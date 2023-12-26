package otus.gpb.recyclerview

import androidx.recyclerview.widget.DiffUtil

class ChatItemCallback : DiffUtil.ItemCallback<Chat>() {

    override fun areItemsTheSame(p0: Chat, p1: Chat): Boolean =
        p0.title == p1.title

    override fun areContentsTheSame(p0: Chat, p1: Chat): Boolean =
        p0 == p1
}