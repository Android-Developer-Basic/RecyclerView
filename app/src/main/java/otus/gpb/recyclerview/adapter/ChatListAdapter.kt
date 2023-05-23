package otus.gpb.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import otus.gpb.recyclerview.ChatItem
import otus.gpb.recyclerview.databinding.ChatItemLayoutBinding
import java.util.Collections

class ChatListAdapter(
    private val listener: OnInteractionListener
) : ListAdapter<ChatItem, ChatViewHolder>(ChatDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            ChatItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        val item = currentList[position]
        holder.itemView.setOnClickListener {
            listener.onBindingClick(item, position)
        }
        holder.bind(item)
    }

    fun swapItem(fromPos: Int, toPos: Int) {
        val newList = currentList.toMutableList()
        if (fromPos < toPos) {
            for (i in fromPos until toPos){
                Collections.swap(newList, i, i + 1)
            }
        } else {
            for (i in fromPos downTo toPos){
                Collections.swap(newList, i, i - 1)
            }
        }
        submitList(newList)
    }

    fun addItem(item: ChatItem) {
        submitList(currentList + item)
    }

    fun addItems(items: List<ChatItem>) {
        submitList(currentList + items)
    }

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }

    fun restoreItem(item: ChatItem, position: Int) {
        val newList = currentList.toMutableList()
        newList.add(position, item)
        submitList(newList)
    }
}