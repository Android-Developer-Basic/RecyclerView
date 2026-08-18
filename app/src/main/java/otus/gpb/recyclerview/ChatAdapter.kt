package otus.gpb.recyclerview

import android.graphics.drawable.GradientDrawable
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

class ChatAdapter : ListAdapter<Chat, ChatAdapter.ChatViewHolder>(DiffCallback) {

    init {
        setHasStableIds(true)
        stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImage = itemView.findViewById<ImageView>(R.id.avatarImage)
        private val avatarInitials = itemView.findViewById<TextView>(R.id.avatarInitials)
        private val online = itemView.findViewById<View>(R.id.onlineIndicator)
        private val title = itemView.findViewById<TextView>(R.id.chatTitle)
        private val verified = itemView.findViewById<ImageView>(R.id.verifiedBadge)
        private val muted = itemView.findViewById<ImageView>(R.id.mutedBadge)
        private val message = itemView.findViewById<TextView>(R.id.lastMessage)
        private val typingDots = itemView.findViewById<ImageView>(R.id.typingDots)
        private val time = itemView.findViewById<TextView>(R.id.chatTime)
        private val delivery = itemView.findViewById<View>(R.id.deliveryState)
        private val firstCheck = itemView.findViewById<ImageView>(R.id.firstCheck)
        private val unread = itemView.findViewById<TextView>(R.id.unreadBadge)
        private val pinned = itemView.findViewById<ImageView>(R.id.pinnedBadge)
        private val mention = itemView.findViewById<ImageView>(R.id.mentionBadge)
        private val trailingStatus = itemView.findViewById<View>(R.id.trailingStatus)

        fun bind(chat: Chat) {
            avatarImage.isVisible = chat.avatarResId != null
            avatarInitials.isVisible = chat.avatarResId == null
            chat.avatarResId?.let(avatarImage::setImageResource)
            avatarInitials.text = chat.avatarLetters
            avatarInitials.background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(chat.avatarColor)
            }

            online.isVisible = chat.isOnline
            title.text = chat.title
            verified.isVisible = chat.isVerified
            muted.isVisible = chat.isMuted
            time.text = chat.time

            val isTyping = chat.messageState == Chat.MessageState.TYPING
            typingDots.isVisible = isTyping
            message.text = chat.message
            message.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    when (chat.messageState) {
                        Chat.MessageState.TYPING -> R.color.telegram_typing
                        Chat.MessageState.DRAFT -> R.color.telegram_red
                        Chat.MessageState.REGULAR -> R.color.text_secondary
                    },
                ),
            )

            delivery.isVisible = chat.deliveryState != Chat.DeliveryState.NONE
            firstCheck.isVisible = chat.deliveryState == Chat.DeliveryState.READ

            val hasUnread = chat.unreadCount > 0 && !chat.hasMention
            unread.isVisible = hasUnread
            unread.text = itemView.resources.getString(R.string.unread_count, chat.unreadCount)
            mention.isVisible = chat.hasMention
            pinned.isVisible = chat.isPinned && !hasUnread && !chat.hasMention
            trailingStatus.isVisible = hasUnread || chat.hasMention || pinned.isVisible
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Chat, newItem: Chat) = oldItem == newItem
    }
}