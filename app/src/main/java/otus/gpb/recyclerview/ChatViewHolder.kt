package otus.gpb.recyclerview

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bind(item: ChatItem) {
        itemView.findViewById<AppCompatImageView>(R.id.avatar_image).setImageResource(item.avatar)
        itemView.findViewById<AppCompatTextView>(R.id.title).text = item.title
        itemView.findViewById<AppCompatImageView>(R.id.verification_image)?.apply {
            if (item.verification == ChatItem.VerificationStatus.UNVERIFIED) {
                visibility = View.GONE
            }
            else {
                visibility = View.VISIBLE
                setImageResource(
                    when (item.verification) {
                        ChatItem.VerificationStatus.VERIFIED -> R.drawable.verified
                        else -> R.drawable.scam
                    }
                )
            }
            if (item.verification == ChatItem.VerificationStatus.SCAM) {
                val imageWidth = layoutParams.width * 2
                layoutParams.width = imageWidth
            }
        }
        itemView.findViewById<AppCompatImageView>(R.id.notification_image)?.apply {
            if (item.notification == ChatItem.NotificationStatus.MUTED) {
                visibility = View.VISIBLE
                setImageResource(R.drawable.muted)
            }
            else {
                visibility = View.GONE
            }
        }
        itemView.findViewById<AppCompatTextView>(R.id.author)?.apply {
            if (item.author.isEmpty()) {
                visibility = View.GONE
            }
            else {
                visibility = View.VISIBLE
                text = item.author
            }
        }
        itemView.findViewById<AppCompatTextView>(R.id.message).text = item.message
        itemView.findViewById<AppCompatImageView>(R.id.message_status_image)?.apply {
            if (item.messageStatus == ChatItem.MessageStatus.UNDELIVERED) {
                visibility = View.GONE
            }
            else {
                visibility = View.VISIBLE
                setImageResource(
                    when (item.messageStatus) {
                        ChatItem.MessageStatus.READ -> R.drawable.read
                        else -> R.drawable.unread
                    }
                )
            }
        }
        itemView.findViewById<AppCompatTextView>(R.id.date_time).text = item.dateTime
        val circle = itemView.findViewById<AppCompatImageView>(R.id.circle_image)
        val count = itemView.findViewById<AppCompatTextView>(R.id.message_count)

        if (item.messageCount == 0) {
            circle.visibility = View.INVISIBLE
            count.visibility = View.INVISIBLE
        }
        else {
            circle.visibility = View.VISIBLE
            count.visibility = View.VISIBLE
            count.text = item.messageCount.toString()
        }
        itemView.findViewById<AppCompatTextView>(R.id.message_count)?.apply {
            if (item.messageCount == 0) {
                text = item.messageCount.toString()
            }
        }
    }
}