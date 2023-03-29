package otus.gpb.recyclerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemLayoutBinding

class ChatAdapter : RecyclerView.Adapter<ChatItemViewHolder>() {

    private val list = mutableListOf<ChatItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val binding =
            ChatItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    fun populate(data: List<ChatItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}

class ChatItemViewHolder(
    private val binding: ChatItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChatItem) {
        with(binding) {
            userName.text = item.userName
            messageText.text = item.lastMessage
            date.text = item.date

            userAvatar.setImageResource(R.drawable.stub_ava)

            setIcons(this, item)
        }
    }

    private fun setIcons(binding: ChatItemLayoutBinding, item: ChatItem) {
        if (item.isMuted) binding.mutedImage.visibility = View.VISIBLE
        if (item.isVerified) binding.verifiedImage.visibility = View.VISIBLE
        if (item.hasUnreadMessage) binding.unreadMessage.visibility = View.VISIBLE
        if (item.isScam) binding.scamIcon.visibility = View.VISIBLE

        if (item.isLastMessageMine) binding.messageStatus.visibility = View.VISIBLE
        binding.messageStatus.isSelected = item.isMessageDelivered && item.isMessageRead

    }
}