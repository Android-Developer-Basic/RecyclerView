package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView by lazy { itemView.findViewById(R.id.name) }
    private val subtitle: TextView by lazy { itemView.findViewById(R.id.subtitle) }
    private val message: TextView by lazy { itemView.findViewById(R.id.message) }
    private val avatar: ImageView by lazy { itemView.findViewById(R.id.avatar) }

    private val isVerified: ImageView by lazy { itemView.findViewById(R.id.verified) }
    private val isMuted: ImageView by lazy { itemView.findViewById(R.id.muted) }

    private val messageStatus: ImageView by lazy { itemView.findViewById(R.id.message_status) }
    private val dateTime: TextView by lazy { itemView.findViewById(R.id.dateTimeText) }

    private val badge: ImageView by lazy { itemView.findViewById(R.id.badge) }
    private val unreadCounter: TextView by lazy { itemView.findViewById(R.id.unread_counter) }

    fun bind(item: Message) {
        name.text = item.name
        subtitle.text = item.subtitle
        message.text = item.message
        avatar.setImageResource(R.drawable.ic_launcher_foreground)

        isVerified.visibility = if (item.isVerified) View.VISIBLE else View.INVISIBLE

        isMuted.visibility = if (item.isMuted) View.VISIBLE else View.INVISIBLE

        messageStatus.setImageResource(
                if (item.isRead) R.drawable.baseline_done_all_24 else R.drawable.baseline_done_24
                                      )

        dateTime.text = item.dateTimeText

        if (item.unreadCounter != null) {
            badge.visibility = View.VISIBLE
            unreadCounter.visibility = View.VISIBLE
            unreadCounter.text = item.unreadCounter.toString()
        } else {
            badge.visibility = View.INVISIBLE
            unreadCounter.visibility = View.INVISIBLE
        }
    }
}