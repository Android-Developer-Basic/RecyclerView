package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class ChatAdapter(private val chatItems: MutableList<Chat>) : ListAdapter<Chat, ChatItemViewHolder>(ChatItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder =
        ChatItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_foreground, parent, false),
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_background, parent, false))

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList().apply {
            removeAt(position)
        }
        chatItems.removeAt(position)
        submitList(newList)
    }
}