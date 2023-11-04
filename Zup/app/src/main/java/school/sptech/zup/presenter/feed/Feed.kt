package school.sptech.zup.presenter.feed

import FeedAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import okhttp3.OkHttpClient

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import school.sptech.zup.R
import school.sptech.zup.domain.model.FeedRequest


class Feed : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        recyclerView = findViewById(R.id.recyclerViewFeed)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FeedAdapter(emptyList())
        recyclerView.adapter = adapter

        // Inicializa o Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.172.32.189:8080") // Substitua pela URL real
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()

        val service = retrofit.create(FeedService::class.java)

        // Faz a solicitação de rede usando Retrofit
        val call = service.getFeed()
        call.enqueue(object : retrofit2.Callback<List<FeedRequest>> {
            override fun onResponse(call: Call<List<FeedRequest>>, response: retrofit2.Response<List<FeedRequest>>) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    adapter.updateData(posts)
                } else {
                    // Lidar com erros, como falha na solicitação
                }
            }

            override fun onFailure(call: Call<List<FeedRequest>>, t: Throwable) {
                // Lidar com falhas de rede
            }
        })
    }

    interface FeedService {
        @GET("/noticia/rss/forbes")
        fun getFeed(): Call<List<FeedRequest>>
    }
}
