import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse

class FeedAdapter(private var feedItems: List<FeedResponse>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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


        // Adicione outros elementos do layout, se necessário
    }
}
