package otus.gpb.recyclerview

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : ListAdapter<Chat, ChatAdapter.ChatViewHolder>(ChatDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val avatar: TextView = view.findViewById(R.id.avatar)
        private val title: TextView = view.findViewById(R.id.title)
        private val mute: ImageView = view.findViewById(R.id.mute)
        private val verified: ImageView = view.findViewById(R.id.verified)
        private val scam: TextView = view.findViewById(R.id.scam)
        private val status: ImageView = view.findViewById(R.id.status)
        private val time: TextView = view.findViewById(R.id.time)
        private val author: TextView = view.findViewById(R.id.author)
        private val preview: ImageView = view.findViewById(R.id.preview)
        private val message: TextView = view.findViewById(R.id.message)
        private val mention: ImageView = view.findViewById(R.id.mention)
        private val pinned: ImageView = view.findViewById(R.id.pinned)
        private val counter: TextView = view.findViewById(R.id.counter)

        fun bind(chat: Chat) {
            val context = itemView.context

            avatar.text = initialsOf(chat.title)
            avatar.backgroundTintList = ColorStateList.valueOf(AVATAR_COLORS[(chat.id % AVATAR_COLORS.size).toInt()])

            title.text = chat.title
            mute.isVisible = chat.isMuted
            verified.isVisible = chat.isVerified
            scam.isVisible = chat.isScam

            status.isVisible = chat.status != MessageStatus.NONE
            when (chat.status) {
                MessageStatus.SENT -> status.setImageResource(R.drawable.ic_check)
                MessageStatus.READ -> status.setImageResource(R.drawable.ic_double_check)
                MessageStatus.NONE -> Unit
            }
            time.text = chat.time

            author.isVisible = chat.author != null
            author.text = chat.author

            preview.isVisible = chat.previewColor != null
            chat.previewColor?.let { preview.backgroundTintList = ColorStateList.valueOf(it) }
            message.text = chat.message

            mention.isVisible = chat.hasMention
            pinned.isVisible = chat.isPinned && chat.unreadCount == 0
            counter.isVisible = chat.unreadCount > 0
            counter.text = chat.unreadCount.toString()
            val counterColor = if (chat.isMuted) R.color.chat_counter_muted else R.color.chat_accent
            counter.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, counterColor))
        }

        private fun initialsOf(title: String): String = title
            .split(' ')
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString("") { it.first().uppercase() }
    }

    private object ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem
    }

    private companion object {
        val AVATAR_COLORS = intArrayOf(
            0xFFE17076.toInt(), 0xFFFAA774.toInt(), 0xFFA695E7.toInt(), 0xFF7BC862.toInt(),
            0xFF6EC9CB.toInt(), 0xFF65AADD.toInt(), 0xFFEE7AAE.toInt(),
        )
    }
}
