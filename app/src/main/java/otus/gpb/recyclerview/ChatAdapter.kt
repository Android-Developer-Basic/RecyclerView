package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatBinding

class ChatAdapter(private var chats: MutableList<Chat>) : RecyclerView.Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    fun addItems(items: List<Chat>) {
        chats.addAll(items)
        notifyItemRangeInserted(chats.size - items.size, items.size)
    }

    fun removeItem(position: Int) {
        chats.removeAt(position)
        notifyItemRemoved(position)
    }
}