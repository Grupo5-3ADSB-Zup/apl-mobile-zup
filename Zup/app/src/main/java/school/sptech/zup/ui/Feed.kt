package school.sptech.zup.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import school.sptech.zup.R
import school.sptech.zup.adapter.PostAdapter
import school.sptech.zup.model.EventoFeed


class Feed : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFeed)
        val postsList = getPosts()
        val adapter = PostAdapter(postsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getPosts(): List<EventoFeed> {

        return listOf(
            EventoFeed("Gazeta", 1, "tudo uma farça"),
            EventoFeed("", 2,""),

        )
    }
}
