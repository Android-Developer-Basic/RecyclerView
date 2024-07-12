package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView

enum class MessageState {
    IS_READ,
    IS_SENT,
    IS_NOT_INCOMING
}

class ChatAdapter(private val items: MutableList<ChatItem>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView by lazy { itemView.findViewById(R.id.nameTV) }
        private val isMuted: ImageView by lazy { itemView.findViewById(R.id.MutedIcon) }
        private val isVerified: ImageView by lazy { itemView.findViewById(R.id.VerifiedIcon) }
        private val isScam: TextView by lazy { itemView.findViewById(R.id.ScamIcon) }
        private val title: TextView by lazy { itemView.findViewById(R.id.titleTV) }
        private val hasPrevPic: ImageView by lazy { itemView.findViewById(R.id.prevPic) }
        private val message: TextView by lazy { itemView.findViewById(R.id.messageTV) }
        private val time: TextView by lazy { itemView.findViewById(R.id.timeTV) }
        private val time: TextView by lazy { itemView.findViewById(R.id.timeTV) }
        private val image: ImageView by lazy { itemView.findViewById(R.id.imageAvatar) }
        fun bind(item: ChatItem){
            name.text = item.name
            if (item.isMuted) isMuted.setImageResource(R.drawable.volume_off)
            if (item.isVerified) isVerified.setImageResource(R.drawable.check_decagram)
            if (!item.isScam) isScam.visibility = View.GONE
            if (item.hasPrevPic) hasPrevPic.setImageResource(R.drawable.prevpic) else {
                hasPrevPic.visibility = View.GONE
                val params = message.layoutParams as ConstraintLayout.LayoutParams
                params.marginStart = 0
                message.layoutParams = params
            }
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