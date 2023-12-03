package otus.gpb.recyclerview.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import otus.gpb.recyclerview.R

class ChatViewHolder(view: View) : ViewHolder(view) {
    val avatar = view.findViewById<ImageView>(R.id.avatar)
    val nameChat = view.findViewById<TextView>(R.id.nameChat)
    val titleChat = view.findViewById<TextView>(R.id.titleChat)
    val imagePreview = view.findViewById<ImageView>(R.id.imagePreview)
    val previewText = view.findViewById<TextView>(R.id.previewText)
    val notMute = view.findViewById<ImageView>(R.id.notMute)
    val textTime = view.findViewById<TextView>(R.id.textTime)
    val countMessage = view.findViewById<TextView>(R.id.countMessage)
}