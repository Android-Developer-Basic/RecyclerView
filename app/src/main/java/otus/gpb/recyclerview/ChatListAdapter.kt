package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ChatListAdapter :
    ListAdapter<ChatItem, RecyclerView.ViewHolder>(DiffChatItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChatListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChatListViewHolder).bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    fun removeItem(fromPosition: Int) {
        val list = currentList.toMutableList()
        list.removeAt(fromPosition)
        submitList(list)
    }

    fun addItems(count: Int) {
        val list = currentList.toMutableList()
        list.addAll(ChatItemDataGenerator.generateChatItemsList(count))
        submitList(list)
    }

    class DiffChatItemCallback : DiffUtil.ItemCallback<ChatItem>() {

        override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
            return oldItem == newItem
        }
    }
}