package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.databinding.ActivityBuscarInfluenciadoresBinding
import school.sptech.zup.databinding.ActivityFiltroPerfilBinding
import school.sptech.zup.network.ServiceProvider
import school.sptech.zup.presenter.list.adapter.ItemPerfilUsuarioAdapter

class FiltroPerfil : AppCompatActivity() {

    val binding by lazy {
        ActivityFiltroPerfilBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemPerfilUsuarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerViewPerfilUsuario)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemPerfilUsuarioAdapter(emptyList())
        recyclerView.adapter = adapter


        //fazer fluxo no cadastro para redirecionamento e depois fazer chamada para salvar local
        //todas as informações
        val call = ServiceProvider.service.BuscaTodosUsuariosInfluencersTpPerfil(2)
        call.enqueue(object : retrofit2.Callback<List<PerfilUsuarioResponse>> {
            override fun onResponse(
                call: Call<List<PerfilUsuarioResponse>>, response: retrofit2.Response<List<PerfilUsuarioResponse>>
            ) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    adapter.updateData(posts)
                } else {
                    // Lidar com erros, como falha na solicitação
                }
            }

            override fun onFailure(call: Call<List<PerfilUsuarioResponse>>?, t: Throwable?) {
                // Lidar com falhas de rede
            }
        })
    }
}