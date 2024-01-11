package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter: RecyclerView.Adapter<ChatViewHolder>() {

    var chatList = emptyList<Chat>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.chat_layout,p0,false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(p0: ChatViewHolder, p1: Int) {
        p0.bind(chatList[p1])
    }

    fun setChat(list:List<Chat>){
        chatList = list
        notifyDataSetChanged()
    }

    fun deleteChat(position:Int){
        val newChat = chatList.toMutableList()
        newChat.removeAt(position)
        chatList = newChat
        notifyDataSetChanged()
    }
}