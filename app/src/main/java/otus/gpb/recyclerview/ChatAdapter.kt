package otus.gpb.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import otus.gpb.recyclerview.databinding.ItemMessageBinding

class ChatAdapter(private val onClickListener: (Int) -> Unit) : Adapter<ChatViewHolder>() {
    private var chatList = emptyList<ChatModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setChats(newChats: List<ChatModel>){
        chatList = newChats
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setNewChats(newChats: List<ChatModel>){
        chatList += newChats
        notifyDataSetChanged()
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
        return ChatViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }
}