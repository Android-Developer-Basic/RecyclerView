package otus.gpb.recyclerview.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.ChatItem
import otus.gpb.recyclerview.databinding.ChatItemLayoutBinding
import java.util.Collections

class ChatAdapter(
    private val listener: OnInteractionListener
) : RecyclerView.Adapter<ChatViewHolder>() {

    private val _list = mutableListOf<ChatItem>()
    val list: List<ChatItem> get() = _list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            ChatItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        val item = _list[position]
        holder.itemView.setOnClickListener {
            listener.onBindingClick(item, position)
//            item.onClickListener?.invoke(item)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int = _list.size

    fun populate(data: List<ChatItem>) {
        _list.clear()
        _list.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(item: ChatItem, position: Int) {
        _list.add(item)
        notifyItemInserted(position)
    }

    fun addItems(items: List<ChatItem>, position: Int) {
        _list.addAll(items)
        notifyItemRangeInserted(position, items.size)
    }

    fun removeItem(position: Int) {
        _list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: ChatItem, position: Int) {
        _list.add(position, item)
        notifyItemInserted(position)
    }

    fun swapItem(fromPos: Int, toPos: Int) {
        if (fromPos < toPos) {
            for (i in fromPos until toPos){
                Collections.swap(_list, i, i + 1)
            }
        } else {
            for (i in fromPos downTo toPos){
                Collections.swap(_list, i, i - 1)
            }
        }
        notifyItemMoved(fromPos, toPos)
    }
}