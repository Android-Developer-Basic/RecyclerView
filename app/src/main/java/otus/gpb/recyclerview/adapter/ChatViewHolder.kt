package otus.gpb.recyclerview.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.ChatItem
import otus.gpb.recyclerview.R
import otus.gpb.recyclerview.databinding.ChatItemLayoutBinding

class ChatViewHolder(
    private val binding: ChatItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChatItem) {
        with(binding) {
            setupWithItem(item)
        }
    }

    private fun ChatItemLayoutBinding.setupWithItem(item: ChatItem) {
        userName.text = item.userName
        messageText.text = item.lastMessage
        date.text = item.date
        userAvatar.setImageResource(R.drawable.stub_ava)

        mutedImage.setVisibility = item.isMuted
        verifiedImage.setVisibility = item.isVerified
        scamIcon.setVisibility = item.isScam
        messageStatus.setVisibility = item.isLastMessageMine
        messageStatus.isSelected = item.isMessageDelivered && item.isMessageRead

        if (item.unreadMessageCount > 0) {
            unreadMessage.visibility = View.VISIBLE
            unreadMessage.text = item.unreadMessageCount.countToText()
        } else {
            unreadMessage.visibility = View.GONE
        }

        userAvatar.setOnClickListener {
            item.onClickListener?.invoke(item)
        }
    }

    private var View.setVisibility: Boolean
        set(condition) { visibility = if (condition) View.VISIBLE else View.GONE}
        get() { return visibility == View.VISIBLE }

    private fun Int.countToText(): String {
        return if (this > 999) "1k+" else "$this"
    }
}