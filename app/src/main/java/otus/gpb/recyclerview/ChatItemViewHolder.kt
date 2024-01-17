package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatItemViewHolder(view: View, val backgroundView: View) : RecyclerView.ViewHolder(view){

    private val imageViewAvatar: ImageView by lazy { itemView.findViewById(R.id.imageViewAvatar) }
    private val scum: ImageView by lazy { itemView.findViewById(R.id.imageViewScum) }
    private val verified: ImageView by lazy { itemView.findViewById(R.id.iconVerified) }
    private val muted: ImageView by lazy { itemView.findViewById(R.id.iconMuted) }
    private val checkmark: ImageView by lazy { itemView.findViewById(R.id.imageViewCheckMark) }
    private val badgeCounter: ImageView by lazy { itemView.findViewById(R.id.imageViewCounter) }
    private val title: TextView by lazy { itemView.findViewById(R.id.textViewTitle) }
    private val subtitle: TextView by lazy { itemView.findViewById(R.id.textViewSubTitle) }
    private val message: TextView by lazy { itemView.findViewById(R.id.textViewMessage) }
    private val badgeCounterText: TextView by lazy { itemView.findViewById(R.id.textViewCounter) }
    private val dateTimeText: TextView by lazy { itemView.findViewById(R.id.textViewDateTime) }

    fun bind(item: Chat) {
        imageViewAvatar.setImageResource(item.idAvatar)
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

        message.text = item.message

        dateTimeText.text = item.dateTimeText

        checkmark.apply {
            visibility =
                when (true) {
                    item.read -> {
                        setImageResource(R.drawable.check_all)
                        View.VISIBLE
                    }

                    item.sent -> {
                        setImageResource(R.drawable.check)
                        View.VISIBLE
                    }

                    else -> View.GONE
                }
        }

        scum.apply {
            item.scum.takeIf {
                it
            }?.let {
                visibility = View.VISIBLE
            } ?: let {
                visibility = View.GONE
            }
        }

        verified.apply {
            item.verified
                .takeIf { it && !item.scum }
                ?.let {
                    visibility = View.VISIBLE
                }
                ?: let {
                    visibility = View.GONE
                }
        }
        muted.apply {
            item.muted
                .takeIf { it && !item.scum }
                ?.let {
                    visibility = View.VISIBLE
                }
                ?: let {
                    visibility = View.GONE
                }
        }

        item.unreadMessages
            .takeIf { it > 0 }
            ?.let {
                badgeCounter.visibility = View.VISIBLE
                badgeCounterText.text = it.toString()
            }
            ?: let {
                badgeCounter.visibility = View.GONE
                badgeCounterText.text = "0"
            }
    }
}