import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import com.google.gson.Gson
import school.sptech.zup.R
import school.sptech.zup.data.api.ServiceApi.Companion.BASE_URL
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.domain.model.FeedRequest

class Feed : AppCompatActivity() {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewFeed)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainScope.launch {
            val feedItems = getFeedItemsFromApi()
            val adapter = FeedAdapter(feedItems)
            recyclerView.adapter = adapter
        }
    }

    private suspend fun getFeedItemsFromApi(): List<FeedRequest> {
        val request = Request.Builder()
            .url(BASE_URL + "noticias/feed")
            .build()

        val response: Response = client.newCall(request).execute()
        val responseBody = response.body?.string()

        // Use o Gson para desserializar a resposta JSON em uma lista de FeedItem
        return gson.fromJson(responseBody, FeedResponse::class.java)
    }
}
