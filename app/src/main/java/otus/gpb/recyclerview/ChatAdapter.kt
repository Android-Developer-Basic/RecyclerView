package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ItemChatBinding

class ChatAdapter(private val list: MutableList<ItemChat>): RecyclerView.Adapter<ChatViewHolder>() {

    fun addItem(list: List<ItemChat>) {

        this.list.addAll(list)
        notifyDataSetChanged()

    }


    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
