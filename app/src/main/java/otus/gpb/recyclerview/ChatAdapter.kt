package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val items: MutableList<ChatItem>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView by lazy { itemView.findViewById(R.id.nameTV) }
        private val title: TextView by lazy { itemView.findViewById(R.id.titleTV) }
        private val message: TextView by lazy { itemView.findViewById(R.id.messageTV) }
        private val time: TextView by lazy { itemView.findViewById(R.id.timeTV) }
        private val image: ImageView by lazy { itemView.findViewById(R.id.imageAvatar) }
        fun bind(item: ChatItem){
            name.text = item.name
            title.text = item.title
            message.text = item.message
            time.text = item.time
            image.setImageResource(item.image)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dialog_view_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)

    }

}