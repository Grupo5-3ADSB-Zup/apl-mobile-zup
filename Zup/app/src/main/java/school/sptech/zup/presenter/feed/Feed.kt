package school.sptech.zup.presenter.feed

import FeedAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.databinding.ActivityFeedBinding
import school.sptech.zup.domain.model.Sessao
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.ui.BuscarInfluenciadores
import school.sptech.zup.ui.FiltroPerfil
import school.sptech.zup.ui.PerfilUsuarioInfluencer
import school.sptech.zup.ui.PerfilUsuarioSemFormulario
import school.sptech.zup.ui.TelaConfiguracao2


class Feed : AppCompatActivity() {



    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter


    private val binding by lazy {
        ActivityFeedBinding.inflate(layoutInflater)
    }

    val sessao = Sessao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerViewFeed)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Testar amanhã
        //val layoutManager = LinearLayoutManager(this)
        //layoutManager.orientation = LinearLayoutManager.VERTICAL
        //recyclerView.layoutManager = layoutManager

        adapter = FeedAdapter(emptyList())
        recyclerView.adapter = adapter

        val call = service.getFeed()
        call.enqueue(object : retrofit2.Callback<List<FeedResponse>> {
            override fun onResponse(
                call: Call<List<FeedResponse>>, response: retrofit2.Response<List<FeedResponse>>
            ) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    adapter.updateData(posts)
                } else {
                    // Lidar com erros, como falha na solicitação
                }
            }

            override fun onFailure(call: Call<List<FeedResponse>>, t: Throwable) {
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

        menuItemPesquisar.setOnMenuItemClickListener{
            val intent = Intent(this, FiltroPerfil::class.java)
            startActivity(intent)
            true
        }

        menuItemSettings.setOnMenuItemClickListener{
            val intent = Intent(this, TelaConfiguracao2::class.java)
            startActivity(intent)
            true
        }

        menuItemPerfil.setOnMenuItemClickListener {
            val sharedPreferences = getSharedPreferences("ZupShared", Context.MODE_PRIVATE)

            val valorInfluencer = sharedPreferences.getBoolean("influencer", false)

            if(sessao.influencer == true || valorInfluencer == true){
                val intent = Intent(this, PerfilUsuarioInfluencer::class.java)
                startActivity(intent)
            }else {
                val intent = Intent(this, PerfilUsuarioSemFormulario::class.java)
                startActivity(intent)
            }
            true
        }
    }
}
