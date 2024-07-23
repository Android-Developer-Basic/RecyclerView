package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import otus.gpb.recyclerview.databinding.ItemRecyclerBinding

class ChatAdapter(private var chatList: MutableList<Chat>) : Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    fun setChats(newChatList: MutableList<Chat>) {
        chatList = newChatList
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        chatList.removeAt(position)
        notifyItemRemoved(position)
    }
}

class ChatViewHolder(private val binding: ItemRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Chat) {
        binding.imageViewAvatar.setImageResource(model.avatarImage)
        model.previewImage?.let { binding.imageViewPreview.setImageResource(it) }

        binding.textViewNickname.text = model.nickName
        binding.textViewMessage.text = model.message
        binding.textViewMessTime.text = model.lastTime
        binding.textViewMessCount.text = model.messCount.toString()

        if (model.group.isEmpty())
            binding.textViewGroup.visibility = View.GONE
        else
            binding.textViewGroup.text = model.group

        if (model.check) {
            binding.imageViewCheck.setImageResource(R.drawable.one_check)
            binding.imageViewCheck.visibility = View.VISIBLE
        } else if (model.isViewed) {
            binding.imageViewCheck.setImageResource(R.drawable.read_check)
            binding.imageViewCheck.visibility = View.VISIBLE
        } else {
            binding.imageViewCheck.visibility = View.GONE
        }

        if (model.messCount == 0) {
            binding.imageViewMessCountCircle.visibility = View.GONE
            binding.textViewMessCount.visibility = View.GONE
        } else {
            binding.imageViewMessCountCircle.visibility = View.VISIBLE
            binding.textViewMessCount.visibility = View.VISIBLE
            binding.textViewMessCount.text = model.messCount.toString()
        }

        if (model.previewImage == null) {
            binding.imageViewPreview.visibility = View.GONE
        } else {
            binding.imageViewPreview.visibility = View.VISIBLE
            binding.imageViewPreview.setImageResource(model.previewImage!!)
        }

        if (!model.scam)
            binding.imageViewScam.visibility = View.GONE

        if (!model.mute)
            binding.imageViewMute.visibility = View.GONE

        if (!model.verified)
            binding.imageViewVerified.visibility = View.GONE
    }
}