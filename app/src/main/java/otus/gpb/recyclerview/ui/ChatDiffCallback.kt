package otus.gpb.recyclerview.ui

import androidx.recyclerview.widget.DiffUtil
import otus.gpb.recyclerview.data.ChatItem

class ChatDiffCallback : DiffUtil.ItemCallback<ChatItem>() {

    override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
        return when{
            oldItem is ChatItem.Group && newItem is ChatItem.Group -> oldItem.chat.id == newItem.chat.id
            oldItem is ChatItem.User && newItem is ChatItem.User -> oldItem.chat.id == newItem.chat.id
            else -> false
        }
    }
}