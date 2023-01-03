package otus.gpb.recyclerview

import android.view.View.GONE
import android.view.View.VISIBLE
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
            verified.visibility = setVisibility(item.isVerified)
            muted.visibility = setVisibility(item.isMuted)
            status.visibility = setVisibility(item.haveStatus)
            done.visibility = setVisibility(item.isDone && !item.isDoneAll)
            doneAll.visibility = setVisibility(item.isDoneAll)
            messageCountCircle.visibility = setVisibility(item.messageCount > 0)
        }
    }

    private fun setVisibility(flag: Boolean) = if (flag) VISIBLE else GONE
}
