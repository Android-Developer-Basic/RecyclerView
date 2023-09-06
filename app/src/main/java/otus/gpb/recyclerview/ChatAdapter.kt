package otus.gpb.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import otus.gpb.recyclerview.databinding.ItemMessageBinding

class ChatAdapter() : Adapter<ChatViewHolder>() {
    private var chatList = emptyList<ChatModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setChats(newChats: List<ChatModel>){
        chatList = newChats
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setNewChats(newChats: List<ChatModel>){
        chatList += newChats
        notifyItemInserted(itemCount)
    }

    fun deleteChat(position: Int){
        chatList = chatList.toMutableList().apply {
            removeAt(position)
        }.toList()
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}