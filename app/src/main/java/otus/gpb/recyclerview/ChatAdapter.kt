package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.model.ChatModel

class ChatAdapter:RecyclerView.Adapter<ChatViewHolder>() {
    private var items = mutableListOf<ChatModel>()

    fun setItems(newItems:List<ChatModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<ChatModel>) {
        val size = items.size
        items.addAll(newItems)
        notifyItemInserted(size)
    }

    fun removeItem(position: Int) {
        val newItems = items.toMutableList()
        newItems.removeAt(position)
        items = newItems
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat, parent, false)
        val viewHolder = ChatViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }
}