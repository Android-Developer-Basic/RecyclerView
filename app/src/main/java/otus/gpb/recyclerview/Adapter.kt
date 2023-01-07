package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import otus.gpb.recyclerview.databinding.ChatItemBinding

class Adapter (
    private val list: MutableList<chat>,
): androidx.recyclerview.widget.RecyclerView.Adapter<ChatViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ChatViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(item = list[position])
    }

    override fun getItemCount(): Int  = list.size

    fun removeItem(position: Int)
    {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addData() {
        notifyItemRangeChanged(10, 10)
    }
}