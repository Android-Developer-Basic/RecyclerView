package otus.gpb.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import otus.gpb.recyclerview.ChatItem

class ChatDiffUtil : DiffUtil.ItemCallback<ChatItem>() {


    override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
        return oldItem == newItem
    }
}