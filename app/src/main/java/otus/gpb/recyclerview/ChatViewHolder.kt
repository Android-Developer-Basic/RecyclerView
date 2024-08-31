package otus.gpb.recyclerview

import android.view.View.GONE
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatBinding

class ChatViewHolder(private val binding: ChatBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Chat) {
        binding.avatar.setImageResource(model.avatar)
        binding.name.text = model.name

        if (!model.isVerify) binding.verify.visibility = GONE
        if (!model.isMute) binding.mute.visibility = GONE
        if (!model.isScam) binding.scam.visibility = GONE

        if (model.author.isEmpty()) binding.author.visibility = GONE
        else binding.author.text = model.author

        when (model.preview) {
            null -> {
                binding.preview.visibility = GONE
                binding.margin.visibility = GONE
                if (model.message.isEmpty()) binding.message.visibility = GONE
                else binding.message.text = model.message
            }

            else -> {
                binding.preview.setImageResource(model.preview)
                if (model.message.isEmpty()) binding.message.visibility = GONE
                else binding.message.text = model.message
            }
        }

        if (!model.isUnread) binding.unread.visibility = GONE
        if (!model.isRead) binding.read.visibility = GONE
        binding.date.text = model.date

        if (model.counter == 0) binding.counter.visibility = GONE
        else binding.counter.text = model.counter.toString()
    }
}