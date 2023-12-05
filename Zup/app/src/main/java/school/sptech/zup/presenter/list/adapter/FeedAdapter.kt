import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import school.sptech.zup.R
import school.sptech.zup.data.model.ComentarioResponse
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.UsuarioResponse
import school.sptech.zup.domain.model.ComentarioRequest
import school.sptech.zup.domain.model.DadosEnvioTelaGptRequest
import school.sptech.zup.domain.model.DadosEnvioTelaMlRequest
import school.sptech.zup.network.ServiceProvider.service
import school.sptech.zup.ui.Gpt
import school.sptech.zup.ui.TelaExibicaoDadosMl

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
        //val commentSection: LinearLayout = itemView.findViewById(R.id.commentSection)

        init {
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
                    R.id.botao_comentario -> {
                        val id = feedItems[adapterPosition].id
                        val titulo = feedItems[adapterPosition].titulo

                        val (linkImagem, descricaoSemImagem) = tratarDescricao(feedItems[adapterPosition].descricao)
                        val descricaoNoticia = if (linkImagem == null) feedItems[adapterPosition].descricao else descricaoSemImagem
                        val fotoNoticia = linkImagem
                        val link = feedItems[adapterPosition].link
                        val emissora = feedItems[adapterPosition].emissora
                        val dtNoticia = feedItems[adapterPosition].dtNoticia

                        val dados = DadosEnvioTelaMlRequest(
                            id = id,
                            titulo = titulo,
                            descricao = descricaoNoticia,
                            link = link,
                            emissora = emissora,
                            dtNoticia = dtNoticia,
                            fotoNoticia = fotoNoticia
                        )

                        val intent = Intent(itemView.context, TelaExibicaoDadosMl::class.java)
                        intent.putExtra("dados", dados)
                        itemView.context.startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }
    }
    private fun mostrarErroMensagem(message: String) {
        println("Erro dudu")
    }
}

