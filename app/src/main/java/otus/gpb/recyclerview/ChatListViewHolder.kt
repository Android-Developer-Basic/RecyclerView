package otus.gpb.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class ChatListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val userImage = view.findViewById<ShapeableImageView>(R.id.user_avatar_siv)
    private val username = view.findViewById<TextView>(R.id.username_tv)
    private val message = view.findViewById<TextView>(R.id.message_tv)
    private val date = view.findViewById<TextView>(R.id.published_date_tv)

    fun bind(item: ChatItem) {
        userImage.setImageResource(item.userImage)
        username.text = item.username
        message.text = item.message
        date.text = item.date
    }
}