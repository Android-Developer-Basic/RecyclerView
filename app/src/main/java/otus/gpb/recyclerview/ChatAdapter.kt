package otus.gpb.recyclerview

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

class ChatAdapter : ListAdapter<ChatItem, ChatViewHolder> (DiffUtilItem()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ChatViewHolder, position: Int) {
        viewHolder.bind(currentList[position])
    }

    fun removeItem(position: Int){
        val list = currentList.toMutableList()
        list.removeAt(position)
        submitList(list)
        notifyDataSetChanged()
    }

    fun addItems(count: Int) {
        val list = currentList.toMutableList()
        list.addAll(ChatDataGenerator.generateChatItemsList(count))
        submitList(list)
        notifyDataSetChanged()
    }

    class DiffUtilItem : DiffUtil.ItemCallback<ChatItem>() {

        override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem == newItem
        }
    }
}