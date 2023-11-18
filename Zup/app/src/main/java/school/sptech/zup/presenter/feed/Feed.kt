package school.sptech.zup.presenter.feed

import FeedAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.databinding.ActivityFeedBinding
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.ui.BuscarInfluenciadores
import school.sptech.zup.ui.TelaConfiguracoes


class Feed : AppCompatActivity() {



    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter


    private val binding by lazy {
        ActivityFeedBinding.inflate(layoutInflater)
    }



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

        binding.buttonHome.setOnClickListener{
            call
        }

        binding.buttonPesquisarInfluencers.setOnClickListener{
            val intent = Intent(this, BuscarInfluenciadores::class.java)
            startActivity(intent)
        }

        binding.buttonConfiguracoes.setOnClickListener{
            val intent = Intent(this, TelaConfiguracoes::class.java)
            startActivity(intent)
        }
    }
}
