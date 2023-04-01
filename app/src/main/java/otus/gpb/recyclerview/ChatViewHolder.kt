package otus.gpb.recyclerview

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun returnView ():View{
        return itemView.findViewById(R.id.upper_layer)
    }

    fun bind(chatItemData: ChatItemData) {

        itemView.apply {
            findViewById<ImageView>(R.id.avatar).setImageResource(chatItemData.avatar)
            findViewById<TextView>(R.id.name).text = chatItemData.name
            findViewById<TextView>(R.id.message).text = chatItemData.message
            findViewById<TextView>(R.id.time).text = chatItemData.time

            if (chatItemData.cont > 0) {
                findViewById<ImageView>(R.id.counter_icon).visibility = View.VISIBLE
                findViewById<TextView>(R.id.counter_text).apply {
                    text = chatItemData.cont.toString()
                    visibility = View.VISIBLE
                }
            }

            if (chatItemData.author != "") {
                findViewById<TextView>(R.id.author).apply {
                    text = chatItemData.author
                    visibility = View.VISIBLE
                }
            }

            if (chatItemData.isVerified) findViewById<ImageView>(R.id.verified_icon).visibility = View.VISIBLE
            if (chatItemData.isMute) findViewById<ImageView>(R.id.mute_icon).visibility = View.VISIBLE
            if (chatItemData.isScam) findViewById<ImageView>(R.id.scam_icon).visibility = View.VISIBLE
            if (chatItemData.isCheck) findViewById<ImageView>(R.id.check_icon).visibility = View.VISIBLE
            if (chatItemData.isRead) findViewById<ImageView>(R.id.read_icon).visibility = View.VISIBLE
        }
    }
}
