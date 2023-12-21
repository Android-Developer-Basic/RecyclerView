package otus.gpb.recyclerview.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import otus.gpb.recyclerview.R
import otus.gpb.recyclerview.data.model.AccountStatus
import otus.gpb.recyclerview.data.model.Chat
import kotlin.random.Random

class ChatAdapter() : ListAdapter<Chat, ChatViewHolder>(ChatDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = getItem(position)
        with(holder){
            avatar.setImageResource(chat.urlAvatar)
            imagePreview.isGone = true
            nameChat.text = chat.nameUserInChat
            titleChat.text = chat.titleText
            if(chat.countNewMessage==0){
                countMessage.isGone = true
            }else {
                countMessage.text = chat.countNewMessage.toString()
            }
            imagePreview.isGone = true
            if(imagePreview.isGone){
                val newMargin = 0
                val params = previewText.layoutParams as ViewGroup.MarginLayoutParams
                params.leftMargin = newMargin
                previewText.layoutParams = params
            }
            previewText.text = chat.previewText
            textTime.text = chat.timeLastMessage
            val random = Random.nextBoolean()
            notMute.isGone = random
        }

    }
}