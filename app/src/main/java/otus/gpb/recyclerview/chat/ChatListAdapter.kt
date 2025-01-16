package otus.gpb.recyclerview.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import otus.gpb.recyclerview.R


class ChatListAdapter : ListAdapter<ChatData, ChatViewHolder>(ChatDiffutils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))

        if (position == currentList.size-1) {
           val newChatList= currentList + getChatCard(currentList.last().id+1)
            changeChatCard(newChatList)
        }
    }

    private fun changeChatCard(newChatList: List<ChatData>) {
        this.submitList(newChatList)
    }

    fun deleteChatCard(position: Int) {
        val newChatList = currentList.filterIndexed { index, _ -> index != position }
        changeChatCard(newChatList)
    }

    override fun onCurrentListChanged(
        previousList: MutableList<ChatData>,
        currentList: MutableList<ChatData>
    ) {
        super.onCurrentListChanged(previousList, currentList)
    }

    fun createChatCards() {

        val cards = (1..15).map {
            getChatCard(it)
        }.toMutableList()
        changeChatCard(cards)
    }

    private fun getChatCard(position: Int): ChatData {

        return ChatData(
            id = position,
            avatar = R.drawable.avatar_1,
            avatarActionIcon = R.drawable.avatar_action_icon,
            lockIcon = R.drawable.lock_icon,
            title = "Title",
            verifiedIcon = R.drawable.verified_icon,
            messageAuthor = "messageAuthor",
            previewIcon = R.drawable.preview_1,
            message = "message",
            checkIcon = R.drawable.check_icon,
            timeService = "11:00",
            mentionBlock = true,
            pinnedIcon = R.drawable.pinned_icon
        )
    }
}