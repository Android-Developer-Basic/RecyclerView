package otus.gpb.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

enum class MessageState {
    IS_READ,
    IS_SENT,
    IS_INCOMING
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
        private val messageState: ImageView by lazy { itemView.findViewById(R.id.messageState) }
        private val time: TextView by lazy { itemView.findViewById(R.id.timeTV) }
        private val messageCounter: TextView by lazy { itemView.findViewById(R.id.messageCounter) }
        private val image: ImageView by lazy { itemView.findViewById(R.id.imageAvatar) }
        fun bind(item: ChatItem){
            name.text = item.name
            if (item.isMuted) {
                isMuted.visibility = View.VISIBLE
                isMuted.setImageResource(R.drawable.volume_off)
            }
            else isMuted.visibility = View.GONE
            if (item.isVerified) isVerified.setImageResource(R.drawable.check_decagram)
            if (!item.isScam) isScam.visibility = View.GONE
            else isScam.visibility = View.VISIBLE
            if (item.hasPrevPic) {
                val params = message.layoutParams as ConstraintLayout.LayoutParams
                params.marginStart = 6
                message.layoutParams = params
                hasPrevPic.visibility = View.VISIBLE
                hasPrevPic.setImageResource(R.drawable.prevpic)
            }
            else {
                hasPrevPic.visibility = View.GONE
                val params = message.layoutParams as ConstraintLayout.LayoutParams
                params.marginStart = 0
                message.layoutParams = params
            }

            if (item.title.isNullOrEmpty()) {
                title.visibility = View.GONE
            }
            else {
                title.text = item.title
            }
            message.text = item.message

            if (item.messageState == MessageState.IS_SENT) messageState.setImageResource(R.drawable.check_svgrepo_com)
            if (item.messageState == MessageState.IS_READ) messageState.setImageResource(R.drawable.check_read_svgrepo_com)
            if (item.messageState == MessageState.IS_INCOMING) messageState.visibility = View.GONE
            time.text = item.time
            if (item.messageCounter != null) {
                messageCounter.visibility = View.VISIBLE
                messageCounter.text = item.messageCounter.toString()
            }
            else messageCounter.visibility = View.GONE
            image.setImageResource(item.image)

        }
    }
//    init {
//        setHasStableIds(true)
//    }

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