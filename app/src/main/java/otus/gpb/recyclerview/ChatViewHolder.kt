package otus.gpb.recyclerview

import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatViewHolder(private val binding: ChatItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: ChatItem,
    ){
        with(binding){
            tvTitle.text = item.name
            tvMessage.text = item.message
            tvTime.text = item.time
            ivAvatar.setImageResource(item.avatar)
        }
    }
}