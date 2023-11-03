import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import school.sptech.zup.R
import school.sptech.zup.domain.model.FeedRequest

class FeedAdapter(private val feedItems: List<FeedRequest>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feedItem = feedItems[position]
        holder.bind(feedItem)
    }

    override fun getItemCount(): Int {
        return feedItems.size
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val emissoraTextView: TextView = itemView.findViewById(R.id.PostEmissora)
        private val tituloTextView: TextView = itemView.findViewById(R.id.PostTitulo)
        private val descricaoTextView: TextView = itemView.findViewById(R.id.PostDescricao)
        private val imagemView: TextView = itemView.findViewById(R.id.PostImage)

        fun bind(feedItem: FeedRequest) {
            emissoraTextView.text = feedItem.emissora
            tituloTextView.text = feedItem.titulo
            descricaoTextView.text = feedItem.descricao

        }
    }
}
