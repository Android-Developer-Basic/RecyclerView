package otus.gpb.recyclerview

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter:RecyclerView.Adapter<ChatHolder>() {

    private val chatArray = mutableListOf<Chat>()
    private var singleLoading:(()->Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return chatArray.size
    }


    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.bind(chatArray[position])
    }



    @SuppressLint("NotifyDataSetChanged")
    fun addChat(chat: Chat){
        chatArray.add(chat)
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(itemNumb:Int){
        chatArray.removeAt(itemNumb)
        notifyDataSetChanged()
        singleLoading?.invoke()
    }

    fun singleLoadingClickListener(callback:()->Unit){
        singleLoading = callback
    }




}