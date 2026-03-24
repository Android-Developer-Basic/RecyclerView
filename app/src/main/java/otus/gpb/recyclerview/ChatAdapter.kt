package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import otus.gpb.recyclerview.databinding.ChatItemBinding
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

class ChatAdapter : ListAdapter<ChatItem, ChatViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {

            textName.text = item.name
            textStatus.text = item.status
            message.text = item.message
            time.text = item.time

            iconVerified.visibility = if (item.verified) VISIBLE else INVISIBLE
            iconScam.visibility = if (item.scam) VISIBLE else INVISIBLE
            iconMute.visibility = if (item.mute) VISIBLE else INVISIBLE

            counter.apply {
                visibility = if (item.counter > 0) VISIBLE else INVISIBLE
                text = item.counter.toString()
            }

            val iconRes = when {
                item.delivered && item.read -> R.drawable.doublecheck
                item.delivered -> R.drawable.check
                item.read -> R.drawable.doublecheck
                else -> null
            }

            if (iconRes != null) {
                delivered.setImageResource(iconRes)
                delivered.visibility = VISIBLE
            } else {
                delivered.visibility = INVISIBLE
            }
        }
    }
}
