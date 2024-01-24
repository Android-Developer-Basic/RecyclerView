package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : RecyclerView.Adapter<ChatViewHolder>() {

    private var itemsList = mutableListOf<Message>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ChatViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    fun setItems(items: List<Message>) {
        val diffutilsCallback = ChatMessagesComparator(itemsList, items)
        val diff = DiffUtil.calculateDiff(diffutilsCallback)
        itemsList.addAll(items)
        diff.dispatchUpdatesTo(this)
    }

    fun removeItem(position: Int) {
        itemsList.removeAt(position)
        notifyItemRemoved(position)
    }
}