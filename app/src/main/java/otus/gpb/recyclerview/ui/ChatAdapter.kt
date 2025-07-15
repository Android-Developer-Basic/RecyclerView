package otus.gpb.recyclerview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import otus.gpb.recyclerview.data.ChatItem
import otus.gpb.recyclerview.databinding.ItemGroupChatBinding
import otus.gpb.recyclerview.databinding.ItemUserChatBinding
import java.util.Locale

class ChatAdapter(private val onItemClick: (Int) -> Unit):
    ListAdapter<ChatItem,RecyclerView.ViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType){
            VIEW_TYPE_GROUP -> {
                val binding = ItemGroupChatBinding.inflate(inflater, parent, false)
                GroupChatViewHolder(binding)
            }
            VIEW_TYPE_USER -> {
                val binding = ItemUserChatBinding.inflate(inflater, parent, false)
                UserChatViewHolder(binding)
            }
            else -> throw RuntimeException("RuntimeException")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ChatItem.Group -> (holder as GroupChatViewHolder).bind(item)
            is ChatItem.User -> (holder as UserChatViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
            is ChatItem.Group -> VIEW_TYPE_GROUP
            is ChatItem.User -> VIEW_TYPE_USER
        }
    }

    inner class GroupChatViewHolder(private val binding: ItemGroupChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem.Group) {
            binding.groupName.text = item.chat.groupName
            binding.lastUsername.text = item.chat.lastUsername
            Glide.with(binding.root.context)
                .load(item.chat.avatarUrl)
                .centerCrop()
                .into(binding.avatarUrl)
            binding.lastMessage.text = item.chat.lastMessage
            binding.verification.visibility = if (item.chat.isVerified) View.VISIBLE else View.GONE
            binding.mute.visibility = if (item.chat.isMuted){
                binding.badge.isActivated = true
                View.VISIBLE
            } else {
                binding.badge.isActivated = false
                View.GONE
            }
            binding.voip.visibility = if (item.chat.isVoip) View.VISIBLE else View.GONE

            binding.badge.visibility = if (item.chat.isMentioned) View.VISIBLE else View.GONE
            binding.badge.text = String.format(Locale("ru", "RU"), "%d", item.chat.counterMessages)
            binding.messageTime.text = item.chat.messageTime
            binding.root.setOnClickListener { onItemClick(item.chat.id) }
        }
    }

    inner class UserChatViewHolder(private val binding: ItemUserChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem.User) {
            binding.usernameField.text = item.chat.username
            Glide.with(binding.root.context)
                .load(item.chat.avatarUrl)
                .centerCrop()
                .into(binding.avatarUrl)
            binding.lastMessage.text = item.chat.lastMessage
            binding.verification.visibility = if (item.chat.isVerified) View.VISIBLE else View.GONE
            binding.mute.visibility = if (item.chat.isMuted){
                binding.badge.isActivated = true
                View.VISIBLE
            } else {
                binding.badge.isActivated = false
                View.GONE
            }
            binding.scam.visibility = if (item.chat.isScam) View.VISIBLE else View.GONE
            binding.badge.visibility = if (item.chat.counterMessages>0) View.VISIBLE else View.GONE
            binding.badge.text = String.format(Locale("ru", "RU"), "%d", item.chat.counterMessages)
            binding.messageTime.text = item.chat.messageTime
            binding.root.setOnClickListener { onItemClick(item.chat.id) }
        }
    }

    companion object{
        private const val VIEW_TYPE_GROUP = 1
        private const val VIEW_TYPE_USER = 2
    }
}