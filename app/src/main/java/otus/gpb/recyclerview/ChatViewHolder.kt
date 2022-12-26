package otus.gpb.recyclerview

import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import otus.gpb.recyclerview.databinding.ActivityMainBinding
import otus.gpb.recyclerview.databinding.ChatItemBinding
import java.time.format.DateTimeFormatter

class ChatViewHolder(private val binding: ChatItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: chat)
    {
        with(binding)
        {
            FIO.text = item.fio
            Message.text = item.message
            Status.text = item.status
            dateTime.text = item.dateTime
            image.setImageResource(item.draw)
        }
    }
}