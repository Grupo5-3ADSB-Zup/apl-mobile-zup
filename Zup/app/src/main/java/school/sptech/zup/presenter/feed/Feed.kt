package school.sptech.zup.presenter.feed

import FeedAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.network.ServiceProvider.service


class Feed : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

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
            override fun onResponse(call: Call<List<FeedResponse>>, response: retrofit2.Response<List<FeedResponse>>) {
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

        // Colocar chamadas de outras Views Aqui
        // Tanto para Refazer a chamada ao clicar na casinha, quanto as outras
        // Colocar as outras chamadas
        //TESTAR CHAMADA DE NOTICIAS DO MOBILE
    }
}
