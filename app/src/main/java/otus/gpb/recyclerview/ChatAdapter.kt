package otus.gpb.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter: RecyclerView.Adapter<ChatViewHolder>() {
    private val items = mutableListOf<Chat>()
    var onLoadMore: (() -> Unit)? = null

    fun addItems(items: List<Chat>) {
        val oldItems = this.items.toList()
        this.items.addAll(items)
        DiffUtil.calculateDiff(ChatDiffUtilCallback(oldItems, this.items))
            .dispatchUpdatesTo(this)
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChatViewHolder(parent)

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
        if (position == items.size - 1) {
            holder.itemView.post {
                onLoadMore?.invoke()
            }
        }
    }

    override fun getItemCount() =
        items.size
}