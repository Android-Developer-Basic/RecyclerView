package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : RecyclerView.Adapter<ChatViewHolder>() {
    private var list = listOf<ChatItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.dialog_view_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        list.getOrNull(position)?.let{holder.bind(it)}
        holder.bind(list[position])
    }
    fun setItems(items: List<ChatItem>){
        list = items
        notifyDataSetChanged()
    }

}