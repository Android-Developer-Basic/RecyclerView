package otus.gpb.recyclerview.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import otus.gpb.recyclerview.R
import otus.gpb.recyclerview.model.AccountStatus
import otus.gpb.recyclerview.model.Chat
import kotlin.random.Random

class ChatAdapter() : RecyclerView.Adapter<ChatViewHolder>() {

    var chatList  =  listOf<Chat>()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        with(holder){
            avatar.setImageResource(chat.urlAvatar)
            nameChat.text = chat.nameUserInChat
            titleChat.text = chat.titleText
            if(chat.countNewMessage==0){
                countMessage.isGone = true
            }else {
                countMessage.text = chat.countNewMessage.toString()
            }
            imagePreview.isGone = false
            previewText.text = chat.previewText
            textTime.text = chat.timeLastMessage
            val random = Random.nextBoolean()
            notMute.isGone = random
        }

    }
}