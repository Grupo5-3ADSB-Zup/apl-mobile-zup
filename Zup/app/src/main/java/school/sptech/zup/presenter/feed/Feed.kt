package school.sptech.zup.presenter.feed

import FeedAdapter
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
import school.sptech.zup.network.ServiceProvider.service


class Feed : AppCompatActivity() {



    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter


    private val binding by lazy {
        ActivityFeedBinding.inflate(layoutInflater)
    }

    val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigation)
    val commentSection: LinearLayout = findViewById(R.id.commentSection)

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


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.botao_comentario -> {
                    // Alternar a visibilidade da área de comentários
                    if (commentSection.getVisibility() === View.VISIBLE) {
                        commentSection.setVisibility(View.INVISIBLE)
                    } else {
                        commentSection.setVisibility(View.VISIBLE)
                        // Aqui você pode adicionar a lógica para carregar e exibir os comentários existentes.
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

    }
}
