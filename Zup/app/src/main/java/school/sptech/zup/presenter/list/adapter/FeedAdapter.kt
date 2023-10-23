package school.sptech.zup.presenter.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import school.sptech.zup.R
import school.sptech.zup.databinding.ItemFeedBinding
import school.sptech.zup.domain.model.Feed
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FeedAdapter :  ListAdapter<Feed, FeedAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Feed>() {
            override fun areItemsTheSame(
                oldItem: Feed,
                newItem: Feed
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Feed,
                newItem: Feed
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

