package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatAdapter (
    private val list: MutableList<ChatItem>
    ): RecyclerView.Adapter<ChatViewHolder>() {

    fun addItem(list: List<ChatItem>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ChatViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(
            item = list[position])
    }

    override fun getItemCount(): Int = list.size
}