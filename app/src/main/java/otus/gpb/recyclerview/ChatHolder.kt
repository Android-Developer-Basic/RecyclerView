package otus.gpb.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatHolder(val binding: ChatItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(chat: Chat
    ) = with(binding) {
        var i: Int
        when (chat.id){
            1 -> i = R.drawable.mute
            2 -> i = R.drawable.mute
            3 -> i = R.drawable.mute
            4 -> i = R.drawable.mute
            else -> i = R.drawable.mute
        }

        if (chat.isScam){
            scam.visibility = View.VISIBLE
        }

        if (chat.isMute){
            mute.visibility = View.VISIBLE
        }

        if (chat.isSend){
            isRead.visibility = View.VISIBLE
            if (chat.isRead){

                Glide.with(isRead)
                    .load(R.drawable.double_tick)
                    .placeholder(android.R.drawable.ic_popup_sync)
                    .error(android.R.drawable.stat_notify_error)
                    .into(isRead)
            }
        }

        if (chat.isVerified){
            verified.visibility = View.VISIBLE
        }

        Glide.with(avatar)
            .load(i)
            .placeholder(android.R.drawable.ic_popup_sync)
            .error(android.R.drawable.stat_notify_error)
            .into(avatar)

        name.text = chat.name
        message.text = chat.message
        if (chat.isGroup){
            messageAuthor.visibility = View.VISIBLE
            messageAuthor.text = chat.messageAuthor
        }
        when (chat.id){
            1 -> lastMessage.text  = "Tue"
            2 -> lastMessage.text  = "Tue"
            3 -> lastMessage.text  = "Tue"
            4 -> lastMessage.text  = "Tue"
        }
        if (chat.numberMessage > 0){
            numberMessage.visibility = View.VISIBLE
            numberMessageIcon.visibility = View.VISIBLE
            numberMessage.text = chat.numberMessage.toString()
        }

    }



}