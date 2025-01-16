package otus.gpb.recyclerview.chat

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.R

class ChatViewHolder(private val cardView: View) : RecyclerView.ViewHolder(cardView) {

    private val image: ImageView by lazy { cardView.findViewById(R.id.avatar) }

    fun bind(items: ChatData) {
        image.setImageResource(items.avatar)
    }
}