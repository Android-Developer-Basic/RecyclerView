package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import otus.gpb.recyclerview.databinding.ChatItemBinding
import otus.gpb.recyclerview.model.Chat
import otus.gpb.recyclerview.service.ChatService

class ChatAdapter(private val listItem: MutableList<Chat>) : Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    fun removeItem(position: Int) {
        listItem.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItems() {
        listItem.addAll(ChatService().getChatList())
        notifyItemInserted(listItem.lastIndex)
    }
}
