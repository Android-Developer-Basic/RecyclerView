package otus.gpb.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ChatAdapter : RecyclerView.Adapter<ChatViewHolder>() {

    private val list = mutableListOf<ChatItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitData(data: MutableList<ChatItemData>) {
        list.clear()
        list.addAll(data)
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
    }

}