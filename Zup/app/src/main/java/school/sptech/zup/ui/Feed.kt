import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import school.sptech.zup.R
import school.sptech.zup.databinding.ActivityLoginEmailBinding
import school.sptech.zup.domain.model.FeedRequest


class Feed : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginEmailBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // Substitua com o nome do layout da sua tela de feed

        // Configurar o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFeed)
        val postsList = getPosts() // Substitua com a lista real de posts
        val adapter = FeedAdapter(postsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    // Função fictícia para obter uma lista de posts
    private fun getPosts(): List<FeedRequest> {
        val client = OkHttpClient()
        val url = "http://54.165.43.158:8080/mobile/noticias/feed"  // Substitua pela URL real de onde obter os posts

        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()

        return if (response.isSuccessful) {
            val responseBody = response.body?.string() ?: ""
            // Analisar o responseBody para obter a lista de posts (assumindo que os posts estão em JSON)
            // Você precisa criar a classe FeedRequest e implementar a lógica de análise aqui
            // Aqui está um exemplo básico de análise de JSON usando a biblioteca Gson:
            val posts: List<FeedRequest> = Gson().fromJson(responseBody, object : TypeToken<List<FeedRequest>>() {}.type)
            posts
        } else {
            emptyList()  // Se a solicitação falhar, retorne uma lista vazia ou lide com erros de acordo com sua necessidade
        }
    }
}