package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatViewHolder(private val view: View, private val listener: Listener) : RecyclerView.ViewHolder(view) {
    private val nameTV: TextView by lazy { view.findViewById(R.id.nameTV) }
    private val userTV: TextView by lazy { view.findViewById(R.id.userTV) }
    private val messageTV: TextView by lazy { view.findViewById(R.id.messageTV) }
    private val dateTV: TextView by lazy { view.findViewById(R.id.dateTV) }

    fun bind(item: ChatListItem.ChatItem) {
        nameTV.text = item.user.firstOrNull().toString()
        userTV.text = item.user
        messageTV.text = item.message
        dateTV.text = formatDate(item.date)

        view.setOnClickListener {
            listener.onItemClicked(layoutPosition)
        }
    }

    private fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("EEE d", Locale.getDefault())
        return date.format(formatter)
    }
}