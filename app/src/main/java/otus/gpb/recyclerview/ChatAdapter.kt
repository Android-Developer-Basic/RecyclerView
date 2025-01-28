package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.ChatRepository.Companion.listChat

class ChatAdapter : ListAdapter<Chat, ChatAdapter.ChatItemViewHolder>(ChatDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ChatItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun removeItem(position: Int) {
        listChat.removeAt(position)
    }

    inner class ChatItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title = view.findViewById<TextView>(R.id.title)
        private val nickNameMessage = view.findViewById<TextView>(R.id.nick_name_message)
        private val textMessage = view.findViewById<TextView>(R.id.text_message)
        private val lock = view.findViewById<ImageView>(R.id.lock_icon_image_view)
        private val scam = view.findViewById<ImageView>(R.id.scam_image_view)
        private val mute = view.findViewById<ImageView>(R.id.mute_image_view)
        private val verified = view.findViewById<ImageView>(R.id.verified_image_view)
        private val dateMessageAndCheck = view.findViewById<TextView>(R.id.date_message_and_check)
        fun bind(chat: Chat) {
            title.text = chat.title
            nickNameMessage.text = chat.lastWriter
            textMessage.text = chat.lastMessage
            if (chat.lock) {
                lock.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                lock.layoutParams.width = 0
            }

            if (chat.scam) {
                scam.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                scam.layoutParams.width = 0
            }

            if (chat.mute) {
                mute.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                mute.layoutParams.width = 0
            }

            if (chat.verified) {
                verified.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                verified.layoutParams.width = 0
            }

            dateMessageAndCheck.text = chat.dateMessage

            if (chat.messageCheck) {
                val leftDrawable =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.check);
                dateMessageAndCheck.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);
            } else{
                val leftDrawable =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.two_check);
                dateMessageAndCheck.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);
            }

            if (chat.verified) {
                verified.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                verified.layoutParams.width = 0
            }
        }
    }
}

class ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {

    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean =
        oldItem == newItem
}