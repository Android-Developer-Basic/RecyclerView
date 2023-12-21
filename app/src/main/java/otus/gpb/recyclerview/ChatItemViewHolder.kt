package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatItemViewHolder(view: View, val backgroundView: View) : RecyclerView.ViewHolder(view) {

    private val icon: ImageView by lazy { itemView.findViewById(R.id.icon) }
    private val title: TextView by lazy { itemView.findViewById(R.id.title) }
    private val subtitle: TextView by lazy { itemView.findViewById(R.id.subtitle) }
    private val text: TextView by lazy { itemView.findViewById(R.id.text) }
    private val dateTimeText: TextView by lazy { itemView.findViewById(R.id.dateTimeText) }

    fun bind(item: Chat) {
        icon.setImageResource(R.drawable.ic_launcher_foreground)
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
        subtitle.text = item.subtitle
        text.text = item.text
        dateTimeText.text = item.dateTimeText
    }

}