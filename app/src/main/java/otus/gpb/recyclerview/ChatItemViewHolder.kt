package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatItemViewHolder(view: View, val backgroundView: View) : RecyclerView.ViewHolder(view) {

    private val icon: ImageView by lazy { itemView.findViewById(R.id.icon) }
    private val checkmark: ImageView by lazy { itemView.findViewById(R.id.checkmark) }
    private val verified: ImageView by lazy { itemView.findViewById(R.id.verified) }
    private val muted: ImageView by lazy { itemView.findViewById(R.id.muted) }
    private val badge: ImageView by lazy { itemView.findViewById(R.id.badge) }
    private val title: TextView by lazy { itemView.findViewById(R.id.title) }
    private val subtitle: TextView by lazy { itemView.findViewById(R.id.subtitle) }
    private val text: TextView by lazy { itemView.findViewById(R.id.text) }
    private val badgeText: TextView by lazy { itemView.findViewById(R.id.badgeText) }
    private val dateTimeText: TextView by lazy { itemView.findViewById(R.id.dateTimeText) }

    fun bind(item: Chat) {
        icon.setImageResource(R.drawable.ic_launcher_foreground)
        title.text = item.title
        subtitle.apply {
            item.subtitle
                .takeIf { it.isNotEmpty() }
                ?.let {
                    text = it
                    visibility = View.VISIBLE
                }
                ?: let {
                    visibility = View.GONE
                }
        }
        text.text = item.text
        dateTimeText.text = item.dateTimeText
        checkmark.apply {
            visibility = if (item.read) {
                setImageResource(R.drawable.done_all_fill1_wght400_grad0_opsz24)
                View.VISIBLE
            } else if (item.sent) {
                setImageResource(R.drawable.done_fill1_wght400_grad0_opsz24)
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        verified.apply {
            item.verified
                .takeIf { it }
                ?.let {
                    setImageResource(R.drawable.verified_fill1_wght400_grad0_opsz24)
                    visibility = View.VISIBLE
                }
                ?: let {
                    visibility = View.GONE
                }
        }
        muted.apply {
            item.muted
                .takeIf { it }
                ?.let {
                    setImageResource(R.drawable.volume_off_fill1_wght400_grad0_opsz24)
                    visibility = View.VISIBLE
                }
                ?: let {
                    visibility = View.GONE
                }
        }
        item.unreadMessages
            .takeIf { it > 0 }
            ?.let {
                badge.setImageResource(R.drawable.circle_fill1_wght400_grad0_opsz24)
                badge.visibility = View.VISIBLE
                badgeText.text = it.toString()
            }
            ?: let {
                badge.visibility = View.GONE
                badgeText.text = "0"
            }
    }

}