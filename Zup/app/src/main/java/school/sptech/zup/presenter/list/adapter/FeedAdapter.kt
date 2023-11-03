package school.sptech.zup.presenter.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import school.sptech.zup.databinding.ItemFeedBinding
import school.sptech.zup.domain.model.FeedRequest

class FeedAdapter :  ListAdapter<FeedRequest, FeedAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FeedRequest>() {
            override fun areItemsTheSame(
                oldItem: FeedRequest,
                newItem: FeedRequest
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FeedRequest,
                newItem: FeedRequest
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFeedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = getItem(position)


        holder.binding.PostDescricao.text
        holder.binding.PostTitulo.text
        holder.binding.PostImage

    }

    inner class ViewHolder(val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root)

}

