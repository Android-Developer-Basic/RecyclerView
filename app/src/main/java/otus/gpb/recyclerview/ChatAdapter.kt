package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter: RecyclerView.Adapter<PersonViewHolder>() {
    private var chatList = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun getItemCount() = chatList.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val chat = chatList[position]
        holder.bind(chat)
    }

    fun setItems(items: MutableList<Item>) {
        chatList = items
        notifyDataSetChanged()
    }

    fun addItems(items: MutableList<Item>) {
        chatList.addAll(items)
        notifyItemRangeChanged(1,10)
    }

    fun removeItem(adapterPosition: Int) {
        val newList = chatList.toMutableList().apply {
            removeAt(adapterPosition)
        }
        setItems(newList)
    }
}