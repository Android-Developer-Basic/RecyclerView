package otus.gpb.recyclerview

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun returnUpperView(): View {
        return itemView.findViewById(R.id.upper_layer)
    }

    fun bind(chatItemData: ChatItemData) {

        itemView.apply {
            findViewById<ImageView>(R.id.avatar).setImageResource(chatItemData.avatar)
            findViewById<TextView>(R.id.name).text = chatItemData.name
            findViewById<TextView>(R.id.message).text = chatItemData.message
            findViewById<TextView>(R.id.time).text = chatItemData.time

            if (chatItemData.cont > 0) {
                findViewById<ImageView>(R.id.counter_icon).isVisible = true
                findViewById<TextView>(R.id.counter_text).apply {
                    text = chatItemData.cont.toString()
                    isVisible = true
                }
            } else {
                findViewById<ImageView>(R.id.counter_icon).isVisible = false
                findViewById<TextView>(R.id.counter_text).isVisible = false
            }

            if (chatItemData.author != "") {
                findViewById<TextView>(R.id.author).apply {
                    text = chatItemData.author
                    isVisible = true
                }
            } else {
                findViewById<TextView>(R.id.author).isVisible = false
            }

            findViewById<ImageView>(R.id.verified_icon).isVisible = chatItemData.isVerified
            findViewById<ImageView>(R.id.mute_icon).isVisible = chatItemData.isMute
            findViewById<ImageView>(R.id.scam_icon).isVisible = chatItemData.isScam
            findViewById<ImageView>(R.id.check_icon).isVisible = chatItemData.isCheck
            findViewById<ImageView>(R.id.read_icon).isVisible = chatItemData.isRead
        }
    }
}
