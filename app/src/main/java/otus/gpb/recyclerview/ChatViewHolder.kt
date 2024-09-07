package otus.gpb.recyclerview

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatBinding

class ChatViewHolder(private val binding: ChatBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Chat) {
        binding.avatar.setImageResource(model.avatar)
        binding.name.text = model.name

        if (!model.isVerify) binding.verify.visibility = GONE
        else binding.verify.visibility = VISIBLE

        if (!model.isMute) binding.mute.visibility = GONE
        else binding.mute.visibility = VISIBLE

        if (!model.isScam) binding.scam.visibility = GONE
        else binding.scam.visibility = VISIBLE

        if (model.author.isEmpty()) binding.author.visibility = GONE
        else {
            binding.author.visibility = VISIBLE
            binding.author.text = model.author
        }

        when (model.preview) {
            null -> {
                binding.preview.visibility = GONE
                binding.margin.visibility = GONE
                displayMessage(model.message)
            }

            else -> {
                binding.preview.visibility = VISIBLE
                binding.margin.visibility = VISIBLE
                binding.preview.setImageResource(model.preview)
                displayMessage(model.message)
            }
        }

        if (!model.isUnread) binding.unread.visibility = GONE
        else binding.unread.visibility = VISIBLE

        if (!model.isRead) binding.read.visibility = GONE
        else binding.read.visibility = VISIBLE

        binding.date.text = model.date

        if (model.counter == 0) binding.counter.visibility = GONE
        else {
            binding.counter.visibility = VISIBLE
            binding.counter.text = model.counter.toString()
        }
    }

    private fun displayMessage(message: String) {
        if (message.isEmpty()) binding.message.visibility = GONE
        else {
            binding.message.visibility = VISIBLE
            binding.message.text = message
        }
    }
}