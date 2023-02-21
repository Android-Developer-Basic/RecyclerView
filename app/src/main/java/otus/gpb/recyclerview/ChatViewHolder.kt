package otus.gpb.recyclerview

import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ItemChatBinding


class ChatViewHolder(private val binding: ItemChatBinding):
    RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemChat) {

            with(binding) {
                name.text = item.name
                message.text = item.message
                status.text = item.status
                time.text = item.time
                pict.setImageResource(item.pict)
            }
        }

}

