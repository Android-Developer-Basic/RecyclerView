package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val listener: Listener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = listOf<ChatListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.CHAT.id -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)

                ChatViewHolder(view, listener)
            }
            ViewTypes.LOAD.id -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.load_item, parent, false)

                LoadViewHolder(view)
            }
            else -> throw IllegalArgumentException("Not found view type for chat adapter")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list.getOrNull(position) ?: return

        when (item) {
            is ChatListItem.ChatItem -> {
                (holder as? ChatViewHolder)?.bind(item)
            }
            is ChatListItem.LoadItem -> {
                return
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is ChatListItem.ChatItem -> ViewTypes.CHAT.id
            is ChatListItem.LoadItem -> ViewTypes.LOAD.id
        }
    }

    override fun getItemCount(): Int = list.size

    fun setItems(items: List<ChatListItem>) {
        list = items
        notifyDataSetChanged()
    }

    enum class ViewTypes(val id: Int) {
        CHAT(R.layout.chat_item),
        LOAD(R.layout.load_item)
    }
}