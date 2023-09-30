package school.sptech.zup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import school.sptech.zup.R

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]

        // Preencher os elementos do layout com os dados do post
        holder.postDescription.text = post.description
        // Carregar a imagem do post usando alguma biblioteca de carregamento de imagem
        // Exemplo: Picasso, Glide, etc.
        // holder.postImage.setImageUrl(post.imageUrl)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImage: ImageView = itemView.findViewById(R.id.postImage)
        val postDescription: TextView = itemView.findViewById(R.id.postDescription)
    }
}
