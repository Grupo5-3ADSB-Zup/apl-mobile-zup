package school.sptech.zup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import school.sptech.zup.R
import school.sptech.zup.model.EventoFeedRequest

class PostAdapter(private val eventoFeeds: List<EventoFeedRequest>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = eventoFeeds[position]

        // Preencher os elementos do layout com os dados do post
        holder.postDescription.text = post.Descricao
        holder.postTitulo.text = post.Titulo

        Picasso.get()
            .load(post.Image)
            .into(holder.postImage)
    }

    override fun getItemCount(): Int {
        return eventoFeeds.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitulo: TextView = itemView.findViewById(R.id.PostTitulo)
        val postImage: ImageView = itemView.findViewById(R.id.PostImage)
        val postDescription: TextView = itemView.findViewById(R.id.PostDescricao)
    }
}
