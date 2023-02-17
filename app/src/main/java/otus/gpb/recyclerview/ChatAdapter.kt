package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatAdapter(private val list: MutableList<ChatItem>): Adapter<ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        val holder = ChatViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItems() {
        list.addAll(ChatDataGenerator().getChatData())
        notifyItemInserted(list.lastIndex)
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}