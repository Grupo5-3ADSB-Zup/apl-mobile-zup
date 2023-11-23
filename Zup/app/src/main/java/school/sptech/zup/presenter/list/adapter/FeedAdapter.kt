import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.domain.model.DadosEnvioTelaGptRequest
import school.sptech.zup.presenter.feed.Feed
import school.sptech.zup.ui.Gpt

@Suppress("DEPRECATION")
class FeedAdapter(private var feedItems: List<FeedResponse>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = feedItems[position]

        val (linkImagem, descricaoSemImagem) = tratarDescricao(post.descricao)

        holder.postDescription.text = if (linkImagem == null) post.descricao else descricaoSemImagem
        holder.postTitulo.text = post.titulo

        linkImagem?.let {
            Glide.with(holder.itemView.context) // Use o contexto do itemView
                .load(it)
                .into(holder.postImage)
            Log.d("FeedAdapter", "Loading image at position $position. Image URL: $it")
        }

        //    val menuItemComentario = holder.barraNoticia.findItem(R.id.botao_comentario)
        //    val menuItemGPT = holder.barraNoticia.findItem(R.id.botao_gpt)

        //    menuItemGPT.setOnMenuItemClickListener{
        //        val intent = Intent(holder.itemView.context, Gpt::class.java)
        //        holder.itemView.context.startActivity(intent)
        //        true
        //    }
    }

    private fun tratarDescricao(descricao: String): Pair<String?, String> {
        val inicioTag = descricao.indexOf("<img src=")

        if (inicioTag >= 0) {
            val inicio = inicioTag + "<img src=\"".length
            val fim = descricao.indexOf("\"", inicio)


            if (inicio >= inicioTag && fim > 0 && fim > inicio) {
                val linkImagem = descricao.substring(inicio, fim)
                val descricaoSemImagem = descricao.substring(fim + 10)

                return Pair(linkImagem, descricaoSemImagem)
            }
        }
        return Pair(null, descricao)
    }

    override fun getItemCount(): Int {
        return feedItems.size
    }

    // Função para atualizar os dados do adaptador
    fun updateData(newData: List<FeedResponse>) {
        feedItems = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitulo: TextView = itemView.findViewById(R.id.PostTitulo)
        val postDescription: TextView = itemView.findViewById(R.id.PostDescricao)
        var postImage: ImageView = itemView.findViewById(R.id.PostImage)
        var barraNoticia: BottomNavigationView = itemView.findViewById(R.id.nav_item_noticia)

        init {
            // Configurar o clique no ícone de comentários
            barraNoticia.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.botao_gpt -> {
                        val id = feedItems[adapterPosition].id
                        val titulo = feedItems[adapterPosition].titulo

                      val  dados = DadosEnvioTelaGptRequest(
                            id = id,
                            titulo = titulo
                        )

                        val intent = Intent(itemView.context, Gpt::class.java)
                        intent.putExtra("dados", dados)
                        itemView.context.startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }
    }
}

