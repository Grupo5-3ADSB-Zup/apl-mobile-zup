package school.sptech.zup.presenter.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.domain.model.FeedRequest

class FeedAdapter(private val feedItems: List<FeedRequest>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = feedItems[position]

        // Preencher os elementos do layout com os dados do post
        holder.postDescription.text = post.descricao
        holder.postTitulo.text = post.titulo
        // Carregar a imagem do post usando alguma biblioteca de carregamento de imagem
        // Exemplo: Picasso, Glide, etc.
//        holder.postImage.setImageUrl(post.imageUrl)
    }

    override fun getItemCount(): Int {
        return feedItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitulo: TextView = itemView.findViewById(R.id.PostTitulo)
        val postDescription: TextView = itemView.findViewById(R.id.PostDescricao)
    }

}






