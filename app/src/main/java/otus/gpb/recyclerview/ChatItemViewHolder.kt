package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatItemViewHolder(
  view: View,
) : RecyclerView.ViewHolder(view) {
  private val img: ImageView by lazy { itemView.findViewById(R.id.img) }
  private val title: TextView by lazy { itemView.findViewById(R.id.title) }
  private val isMuted: View by lazy { itemView.findViewById(R.id.is_muted) }
  private val isVerified: View by lazy { itemView.findViewById(R.id.is_verified) }
  private val isScam: View by lazy { itemView.findViewById(R.id.is_scam) }
  private val lastMessageAuthor: TextView by lazy { itemView.findViewById(R.id.last_message_author) }
  private val lastMessageContainer: View by lazy { itemView.findViewById(R.id.last_message_container) }
  private val lastMessageImgRes: ImageView by lazy { itemView.findViewById(R.id.last_message_img) }
  private val lastMessageText: TextView by lazy { itemView.findViewById(R.id.last_message_text) }
  private val messageSendAndUnread: View by lazy { itemView.findViewById(R.id.message_send_and_unread) }
  private val messageSendAndRead: View by lazy { itemView.findViewById(R.id.message_send_and_read) }
  private val unreadBudge: View by lazy { itemView.findViewById(R.id.unread_budge) }
  private val unreadValue: TextView by lazy { itemView.findViewById(R.id.unread_value) }

  fun bind(item: ChatItem) {
    img.setImageResource(item.imgRes)
    title.text = item.title

    isVerified.visibility = if (item.isVerified) View.VISIBLE else View.GONE
    isMuted.visibility = if (item.isMuted) View.VISIBLE else View.GONE
    isScam.visibility = if (item.isScam) View.VISIBLE else View.GONE

    if (item.lastMessage != null) {
      lastMessageAuthor.visibility = View.VISIBLE
      lastMessageContainer.visibility = View.VISIBLE

      if (item.lastMessage.author != null) {
        lastMessageAuthor.visibility = View.VISIBLE
        lastMessageAuthor.text = item.lastMessage.author
      } else {
        lastMessageAuthor.visibility = View.GONE
      }
      lastMessageText.text = item.lastMessage.title

      if (item.lastMessage.hasImg) {
        lastMessageImgRes.visibility = View.VISIBLE
      } else {
        lastMessageImgRes.visibility = View.GONE
      }
    } else {
      lastMessageAuthor.visibility = View.GONE
      lastMessageContainer.visibility = View.GONE
    }

    if (item.isMessageSend) {
      if (item.isMessageRead) {
        messageSendAndRead.visibility = View.VISIBLE
        messageSendAndUnread.visibility = View.GONE
      } else {
        messageSendAndRead.visibility = View.GONE
        messageSendAndUnread.visibility = View.VISIBLE
      }
    } else {
      messageSendAndUnread.visibility = View.GONE
      messageSendAndRead.visibility = View.GONE
    }

    if (item.unreadValue > 0) {
      unreadBudge.visibility = View.VISIBLE
      unreadValue.text = "${item.unreadValue}"
    } else {
      unreadBudge.visibility = View.GONE
    }
  }
}