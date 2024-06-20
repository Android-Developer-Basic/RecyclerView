package otus.gpb.recyclerview.Adapters

import android.net.http.UrlRequest.Status
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.TimeUtil
import otus.gpb.recyclerview.databinding.ChatLayoutBinding
import otus.gpb.recyclerview.models.ChatItem
import otus.gpb.recyclerview.models.ChatStatus

class ChatAdapter: RecyclerView.Adapter<ChatAdapter.ChatItemViewHolder>() {
    private var items: MutableList<ChatItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatLayoutBinding.inflate(inflater, parent, false)
        return ChatItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(list: List<ChatItem>) {
        items.clear()
        addList(list)
    }
    fun addList(list: List<ChatItem>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun  remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ChatItemViewHolder(private val binding: ChatLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem) {
            binding.title.text = item.title
            binding.img.setImageResource(item.imgRes)
            binding.isMuted.visibility = if (item.isMuted) View.VISIBLE else View.GONE

            binding.status.visibility = if (item.status == ChatStatus.Scam) View.VISIBLE else View.GONE
            binding.statusValue.visibility = if (item.status == ChatStatus.Scam) View.VISIBLE else View.GONE
            binding.statusValue.text = item.status.value

            if (item.lastMessage != null) {
                binding.subject.visibility = View.VISIBLE
                binding.lastMessageContainer.visibility = View.VISIBLE

                if (!item.subject.isEmpty()) {
                    binding.subject.visibility = View.VISIBLE
                    binding.subject.text = item.subject
                } else {
                    binding.subject.visibility = View.GONE
                }
                binding.lastMessageText.text = item.lastMessage.title

                if (item.lastMessage.hasImg) {
                    binding.lastMessageImg.visibility = View.VISIBLE
                } else {
                    binding.lastMessageImg.visibility = View.GONE
                }
            } else {
                binding.subject.visibility = View.GONE
                binding.lastMessageContainer.visibility = View.GONE
            }

            if (item.isMessageSend) {
                if (item.isMessageRead) {
                    binding.messageSendAndRead.visibility = View.VISIBLE
                    binding.messageSendAndUnread.visibility = View.GONE
                } else {
                    binding.messageSendAndRead.visibility = View.GONE
                    binding.messageSendAndUnread.visibility = View.VISIBLE
                }
            } else {
                binding.messageSendAndUnread.visibility = View.GONE
                binding.messageSendAndRead.visibility = View.GONE
            }

            if (item.unReadCount > 0) {
                binding.unreadBudge.visibility = View.VISIBLE
                binding.unreadValue.text = "${item.unReadCount}"
            } else {
                binding.unreadBudge.visibility = View.GONE
            }

            binding.date.text = TimeUtil.getTime(item.date)
        }
    }

}