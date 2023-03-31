package otus.gpb.recyclerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemLayoutBinding

class ChatAdapter(
    private val listener: OnInteractionListener
) : RecyclerView.Adapter<ChatItemViewHolder>() {

    private val list = mutableListOf<ChatItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val binding =
            ChatItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatItemViewHolder(listener,binding)
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

    fun addItem(item: ChatItem) {
        list.add(item)
        notifyItemInserted(list.lastIndex)
    }
}

class ChatItemViewHolder(
    private val listener: OnInteractionListener,
    private val binding: ChatItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChatItem) {
        with(binding) {
            setupWithItem(item)
        }
    }

    private fun ChatItemLayoutBinding.setupWithItem(item: ChatItem) {
        userName.text = item.userName
        messageText.text = item.lastMessage
        date.text = item.date
        userAvatar.setImageResource(R.drawable.stub_ava)

        mutedImage.visibility = setIconVisibility(item.isMuted)
        verifiedImage.visibility = setIconVisibility(item.isVerified)
        scamIcon.visibility = setIconVisibility(item.isScam)
        messageStatus.visibility = setIconVisibility(item.isLastMessageMine)
        messageStatus.isSelected = item.isMessageDelivered && item.isMessageRead

        if (item.unreadMessageCount > 0) {
            unreadMessage.visibility = View.VISIBLE
            unreadMessage.text = item.unreadMessageCount.countToText()
        }

        itemView.setOnClickListener {
            listener.onBindingClick(item)
        }
    }

    private fun setIconVisibility(condition: Boolean): Int {
        return if (condition) View.VISIBLE else View.GONE
    }

    private fun Int.countToText(): String {
        return if (this > 999) "1k+" else "$this"
    }
}