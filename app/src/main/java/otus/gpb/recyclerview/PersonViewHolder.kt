package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

open class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Item) {
        itemView.findViewById<ImageView>(R.id.avatar).setImageResource(item.avatar)
        itemView.findViewById<TextView>(R.id.name).text = item.name
        itemView.findViewById<TextView>(R.id.message).text = item.message
        itemView.findViewById<TextView>(R.id.date).text = item.date
        itemView.findViewById<ImageView>(R.id.mute).isVisible = item.mute
        itemView.findViewById<ImageView>(R.id.verify).isVisible = item.verify

        val comment = itemView.findViewById<TextView>(R.id.comment)
        if (item.comment !="") {
            comment.text = item.comment
            comment.isVisible = true
        } else {
           comment.isVisible = false
        }

        itemView.findViewById<ImageView>(R.id.check).isVisible = item.check
        itemView.findViewById<ImageView>(R.id.doublecheck).isVisible = item.doubleCheck
        itemView.findViewById<ImageView>(R.id.scum).isVisible = item.scum

        val unreadCount = itemView.findViewById<TextView>(R.id.unreadcount)
        if (item.unreadMessages > 0) {
            unreadCount.text = item.unreadMessages.toString()
            unreadCount.isVisible = true
        } else {
            unreadCount.isVisible = false
        }
        itemView.findViewById<ImageView>(R.id.pictureInMessage).isVisible = item.pictureInMessage
    }
}