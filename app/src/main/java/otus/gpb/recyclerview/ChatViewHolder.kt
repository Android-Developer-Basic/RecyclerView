package otus.gpb.recyclerview

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
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
                findViewById<ImageView>(R.id.counter_icon).visibility = VISIBLE
                findViewById<TextView>(R.id.counter_text).apply {
                    text = chatItemData.cont.toString()
                    visibility = VISIBLE
                }
            } else {
                findViewById<ImageView>(R.id.counter_icon).visibility = GONE
                findViewById<TextView>(R.id.counter_text).visibility = GONE
            }

            if (chatItemData.author != "") {
                findViewById<TextView>(R.id.author).apply {
                    text = chatItemData.author
                    visibility = VISIBLE
                }
            } else {
                findViewById<TextView>(R.id.author).visibility = GONE
            }


            if (chatItemData.isVerified) {
                findViewById<ImageView>(R.id.verified_icon).visibility = VISIBLE
            } else findViewById<ImageView>(R.id.verified_icon).visibility = GONE

            if (chatItemData.isMute) {
                findViewById<ImageView>(R.id.mute_icon).visibility = VISIBLE
            } else findViewById<ImageView>(R.id.mute_icon).visibility = GONE

            if (chatItemData.isScam) {
                findViewById<ImageView>(R.id.scam_icon).visibility = VISIBLE
            } else findViewById<ImageView>(R.id.scam_icon).visibility = GONE

            if (chatItemData.isCheck) {
                findViewById<ImageView>(R.id.check_icon).visibility = VISIBLE
            } else findViewById<ImageView>(R.id.check_icon).visibility = GONE

            if (chatItemData.isRead) {
                findViewById<ImageView>(R.id.read_icon).visibility = VISIBLE
            } else findViewById<ImageView>(R.id.read_icon).visibility = GONE
        }
    }
}
