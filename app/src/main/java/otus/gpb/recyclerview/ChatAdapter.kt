package otus.gpb.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    private val chats: MutableList<Chat>,
    private val listener: ChatClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("Adapter", "onCreateViewHolder: $viewType")

        val inflater = LayoutInflater.from(parent.context)
        return ChatViewHolder(inflater.inflate(R.layout.chat_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder: $position")

        when (holder) {
            is ChatViewHolder -> {
                holder.bind(chats[position], listener)
            }
        }
    }

    fun addItems(list: MutableList<Chat>)
    {
        this.chats.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        chats.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = chats.size

    interface ChatClickListener {
        fun onChatClick(chat: Chat, position: Int)
    }
}