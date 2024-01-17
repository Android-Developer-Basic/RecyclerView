package otus.gpb.recyclerview

import androidx.recyclerview.widget.DiffUtil

class ChatItemCallback : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem
}