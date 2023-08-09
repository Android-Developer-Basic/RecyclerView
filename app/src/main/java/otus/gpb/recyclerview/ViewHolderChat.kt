package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(chatItemData: movieModel) {

        itemView.apply {
            findViewById<ImageView>(R.id.imageView_avatar).setImageResource(chatItemData.avatar)
            findViewById<TextView>(R.id.textView_nick).text = chatItemData.name
            findViewById<TextView>(R.id.textView_lastMas).text = chatItemData.message
            findViewById<TextView>(R.id.textView_time).text = chatItemData.time

            if (chatItemData.cont > 0) {
                findViewById<ImageView>(R.id.imageView_circle).visibility = View.VISIBLE
                findViewById<TextView>(R.id.textView_count).apply {
                    text = chatItemData.cont.toString()
                    visibility = View.VISIBLE
                }

            }
            else {findViewById<ImageView>(R.id.imageView_circle).visibility = View.GONE}

            if (chatItemData.author != "") {
                findViewById<TextView>(R.id.textView_subLine).apply {
                    text = chatItemData.author
                    visibility = View.VISIBLE
                }
            }

            findViewById<ImageView>(R.id.imageView_verified_icon).apply {
                visibility = if (chatItemData.isVerified)
                    View.VISIBLE
                else
                    View.GONE
            }
            findViewById<ImageView>(R.id.imageView_mute_icon).apply {
                visibility = if (chatItemData.isMute)
                    View.VISIBLE
                else
                    View.GONE
            }
            findViewById<ImageView>(R.id.imageView_scam_patch).apply {
                visibility = if (chatItemData.isScam)
                    View.VISIBLE
                else
                    View.GONE
            }
            findViewById<ImageView>(R.id.imageView_preview).apply {
                visibility = if (chatItemData.isPreview)
                    View.VISIBLE
                else
                    View.GONE
            }
            findViewById<ImageView>(R.id.imageView_check).apply {
                visibility = if (chatItemData.isCheck)
                    View.VISIBLE
                else
                    View.GONE
            }
            findViewById<ImageView>(R.id.imageView_read_icon).apply {
                visibility = if (chatItemData.isRead)
                    View.VISIBLE
                else
                    View.GONE
            }
        }
    }
    }

