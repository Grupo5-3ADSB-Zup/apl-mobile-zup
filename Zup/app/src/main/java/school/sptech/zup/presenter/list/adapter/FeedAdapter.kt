import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse

class FeedAdapter(private var feedItems: List<FeedResponse>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = feedItems[position]

        val RetirarImagemDescricao = tratarDescricao(post.descricao)
        


        // Preencher os elementos do layout com os dados do post
        holder.postDescription.text =
            (if (RetirarImagemDescricao?.second == null) post.descricao
            else RetirarImagemDescricao.second).toString()

        holder.postTitulo.text = post.titulo
        // Você pode adicionar o código para carregar a imagem do post aqui, usando alguma biblioteca de carregamento de imagem como Picasso, Glide, etc.
    }

    private fun tratarDescricao(descricao: String): Pair<String, String>? {

        val inicioTag = descricao.indexOf("<img src=")

        if (inicioTag >= 0) {
            val inicio = inicioTag + "<img src= ".length
            val fim = descricao.indexOf("/>", inicio)

            // Extrair o link da imagem
            if (inicio >= inicioTag && fim > 0 && fim > inicio) {
                val linkImagem = descricao.substring(inicio, fim)
                val descricaoSemImagem = descricao.substring(fim + 8)

                return Pair(linkImagem, descricaoSemImagem)
            }
        }
        return null
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
        // Adicione outros elementos do layout, se necessário
    }
}
