package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class MainAdapter : RecyclerView.Adapter<ChatViewHolder>()  {

    private var items = mutableListOf<movieModel>()

    fun setItems(newItems: List<movieModel>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(items: List<movieModel>) {
        val size = this.items.size
        this.items.addAll(items)
        notifyItemInserted(size)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun removeItem(position: Int) {
        val newItems = items.toMutableList()
        newItems.removeAt(position)
        items = newItems
        notifyItemRemoved(position)
    }

}