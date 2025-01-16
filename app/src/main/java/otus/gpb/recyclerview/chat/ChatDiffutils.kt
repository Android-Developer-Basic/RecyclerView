package otus.gpb.recyclerview.chat

import androidx.recyclerview.widget.DiffUtil

class ChatDiffutils : DiffUtil.ItemCallback<ChatData>() {

    override fun areItemsTheSame(oldItem: ChatData, newItem: ChatData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatData, newItem: ChatData): Boolean {
        return oldItem.id == newItem.id
    }
}