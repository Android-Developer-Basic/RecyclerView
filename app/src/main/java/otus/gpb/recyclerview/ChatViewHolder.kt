package otus.gpb.recyclerview

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatViewHolder(private val binding: ChatItemBinding): ViewHolder(binding.root) {

    private fun itemVisibility(condition: Boolean) = if (condition) VISIBLE else GONE

    fun bind(item: ChatItem) {
        with(binding){
            image.setImageResource(item.profileImage)
            name.text = item.name
            status.text = item.status
            message.text = item.message
            date.text = item.date
            messageCounter.text = item.messageCounter.toString()
            verified.visibility = itemVisibility(!item.scam)
            chatMuted.visibility = itemVisibility(!item.scam)
            scam.visibility = itemVisibility(item.scam)
            status.visibility = itemVisibility(item.haveStatus)
            sendMessage.visibility = itemVisibility(item.sendMessage && !item.readMessage)
            readMessage.visibility = itemVisibility(item.readMessage)
            counterMessages.visibility = itemVisibility(item.messageCounter > 0)
        }
    }
}