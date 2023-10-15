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
        setContentView(R.layout.activity_feed) // Substitua com o nome do layout da sua tela de feed

        // Configurar o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFeed)
        val postsList = getPosts() // Substitua com a lista real de posts
        val adapter = PostAdapter(postsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    // Função fictícia para obter uma lista de posts
    private fun getPosts(): List<EventoFeed> {
        // Substitua com a lógica real para obter os posts do seu aplicativo
        return listOf(
            EventoFeed("", "Toma Toma safadinha"),
            EventoFeed("url_da_imagem2", "Descrição do Post 2"),
            // Adicione mais posts conforme necessário
        )
    }
}
