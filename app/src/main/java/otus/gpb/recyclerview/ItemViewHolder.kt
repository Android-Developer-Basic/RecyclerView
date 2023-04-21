package otus.gpb.recyclerview

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

open class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    fun bind(itemData: ItemData) {

        itemView.findViewById<ImageView>(R.id.avatar).setImageResource(itemData.avatar)
        itemView.findViewById<TextView>(R.id.chat_name).text = itemData.chatName
        itemView.findViewById<TextView>(R.id.message).text = itemData.message
        itemView.findViewById<ImageView>(R.id.verify_icon).isVisible = itemData.isVerify
        itemView.findViewById<ImageView>(R.id.mute_icon).isVisible = itemData.isMute
        itemView.findViewById<ImageView>(R.id.scam_icon).isVisible = itemData.isScam

        if (itemData.author != "") {
            itemView.findViewById<TextView>(R.id.author).text = itemData.author
            itemView.findViewById<TextView>(R.id.author).isVisible = true
        } else {
            itemView.findViewById<TextView>(R.id.author).isVisible = false
        }

        itemView.findViewById<ImageView>(R.id.check).isVisible = itemData.isCheck
        itemView.findViewById<ImageView>(R.id.double_check).isVisible = itemData.isDoubleCheck
        itemView.findViewById<TextView>(R.id.date_time).text = itemData.time

        itemView.findViewById<ImageView>(R.id.unread_count_icon).isVisible = itemData.unreadCount > 0











    }
}