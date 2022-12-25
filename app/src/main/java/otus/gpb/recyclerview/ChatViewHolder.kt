package otus.gpb.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import otus.gpb.recyclerview.databinding.ChatItemBinding
import otus.gpb.recyclerview.model.Chat

class ChatViewHolder(private val binding: ChatItemBinding) : ViewHolder(binding.root) {

    fun bind(item: Chat) {
        with(binding) {
            image.setImageResource(item.profileImage)
            name.text = item.name
            status.text = item.status
            lastMsgPreview.text = item.lastMessage
            date.text = item.date
            messageCounter.text = item.messageCount.toString()
            verified.visibility = if (item.isVerified) View.VISIBLE else View.GONE
            muted.visibility = if (item.isMuted) View.VISIBLE else View.GONE
            status.visibility = if (item.haveStatus) View.VISIBLE else View.GONE
            done.visibility = if (item.isDone && !item.isDoneAll) View.VISIBLE else View.GONE
            doneAll.visibility = if (item.isDoneAll) View.VISIBLE else View.GONE // пофиксить
            messageCountCircle.visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
        }
    }
}
