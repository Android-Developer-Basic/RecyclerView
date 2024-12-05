package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(): RecyclerView.Adapter<ChatViewHolder>() {
    private var items = emptyList<ChatItem>()

    fun setItems(list: List<ChatItem>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ChatViewHolder(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun removeChat(position:Int){
        val chat = items.toMutableList()
        chat.removeAt(position)
        items = chat
        notifyItemRemoved(position)
    }
}