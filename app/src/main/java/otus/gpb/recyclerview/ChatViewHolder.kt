package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
) {
    private val avatarImageView: ImageView = itemView.findViewById(R.id.avatar)
    private val titleTextView: TextView = itemView.findViewById(R.id.title)
    private val scamImageView: ImageView = itemView.findViewById(R.id.scam)
    private val verifiedImageView: ImageView = itemView.findViewById(R.id.verified)
    private val mutedImageView: ImageView = itemView.findViewById(R.id.mute)
    private val timeTextView: TextView = itemView.findViewById(R.id.time)
    private val statusImageView: ImageView = itemView.findViewById(R.id.status)
    private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitle)
    private val previewImageView: ImageView = itemView.findViewById(R.id.preview)
    private val textTextView: TextView = itemView.findViewById(R.id.text)
    private val unreadTextView: TextView = itemView.findViewById(R.id.unread)

    fun bind(chat: Chat) {
        avatarImageView.setImageResource(chat.avatar)
        titleTextView.text = chat.title
        scamImageView.visibility = if (chat.isScam) View.VISIBLE else View.GONE
        verifiedImageView.visibility = if (chat.isVerified) View.VISIBLE else View.GONE
        mutedImageView.visibility = if (chat.isMuted) View.VISIBLE else View.GONE
        timeTextView.text = chat.time
        when (chat.status) {
            Chat.Status.READ -> statusImageView.setImageResource(R.drawable.read_icon)
            Chat.Status.CHECKED -> statusImageView.setImageResource(R.drawable.check_icon)
            else -> statusImageView.setImageDrawable(null)
        }
        chat.subtitle?.let {
            subtitleTextView.text = it
            subtitleTextView.visibility = View.VISIBLE
        } ?: run {
            subtitleTextView.text = ""
            subtitleTextView.visibility = View.GONE
        }
        chat.preview?.let {
            previewImageView.setImageResource(chat.preview)
            previewImageView.visibility = View.VISIBLE
        } ?: run {
            previewImageView.setImageDrawable(null)
            previewImageView.visibility = View.GONE
        }
        textTextView.text = chat.text
        if (chat.unreadCount > 0) {
            unreadTextView.visibility = View.VISIBLE
            unreadTextView.text = chat.unreadCount.toString()
        } else {
            unreadTextView.visibility = View.GONE
            unreadTextView.text = ""
        }
    }
}