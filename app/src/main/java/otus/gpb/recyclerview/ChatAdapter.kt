package otus.gpb.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding
class ChatAdapter:RecyclerView.Adapter<ChatHolder>() {

    private val chatArray = ArrayList<Chat>()



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


    fun addChat(chat:Chat){
        chatArray.add(chat)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(itemNumb:Int){
        chatArray.removeAt(itemNumb)
        notifyDataSetChanged()
    }




}