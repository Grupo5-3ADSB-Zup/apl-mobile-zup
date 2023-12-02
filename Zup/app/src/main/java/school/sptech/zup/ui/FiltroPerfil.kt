package school.sptech.zup.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.databinding.ActivityBuscarInfluenciadoresBinding
import school.sptech.zup.databinding.ActivityFiltroPerfilBinding
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.presenter.feed.Feed
import school.sptech.zup.presenter.list.adapter.ItemPerfilUsuarioAdapter

class FiltroPerfil : AppCompatActivity() {

    val binding by lazy {
        ActivityFiltroPerfilBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemPerfilUsuarioAdapter

    val sessao = Sessao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerViewPerfilUsuario)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemPerfilUsuarioAdapter(emptyList())
        recyclerView.adapter = adapter

        val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

        val valorIdPerfil = sharedPreferences.getLong("idPerfil", 0)

        val call = service.BuscaUsuariosInfluencerTpPerfil(
            if (valorIdPerfil.toInt() == 0) sessao.idTpPerfil else valorIdPerfil
        )
        call.enqueue(object : retrofit2.Callback<List<PerfilUsuarioResponse>> {
            override fun onResponse(
                call: Call<List<PerfilUsuarioResponse>>,
                response: Response<List<PerfilUsuarioResponse>>
            ) {
                if (response.isSuccessful) {
                    val posts = response?.body()

                    if (posts != null) {
                        binding.idPerfilUsuario.text = posts[0].descPerfil.toString()
                        adapter.updateData(posts)
                    }
                } else {
                    // Lidar com erros, como falha na solicitação
                }
            }

            override fun onFailure(call: Call<List<PerfilUsuarioResponse>>?, t: Throwable?) {
                // Lidar com falhas de rede
            }
        })

        val botaoNavBar = binding.navBar

        val menuItemHome = botaoNavBar.menu.findItem(R.id.botao_home)
        val menuItemPesquisar = botaoNavBar.menu.findItem(R.id.botao_search)
        val menuItemSettings = botaoNavBar.menu.findItem(R.id.botao_settings)
        val menuItemPerfil = botaoNavBar.menu.findItem(R.id.botao_profile)

        menuItemHome.setOnMenuItemClickListener {
            val intent = Intent(this, Feed::class.java)
            startActivity(intent)
            true
        }

        menuItemPesquisar.setOnMenuItemClickListener {
            val intent = Intent(this, FiltroPerfil::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener {
            val intent = Intent(this, TelaConfiguracao2::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

            val valorInfluencer = sharedPreferences.getBoolean("influencer", false)

            if (valorInfluencer) {
                val intent = Intent(this, PerfilUsuarioInfluencer::class.java)
                startActivity(intent)
                true
            } else {
                val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
                startActivity(intent)
                true
            }
        }
    }
}