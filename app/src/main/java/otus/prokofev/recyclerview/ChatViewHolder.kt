package otus.prokofev.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(chatView: View) : RecyclerView.ViewHolder(chatView) {

    private val chatView: View = chatView.findViewById(R.id.chat_view)
    private val chatAvatar: ImageView = chatView.findViewById(R.id.avatar)
    private val chatName: TextView = chatView.findViewById(R.id.name)
    private val chatAuthor: TextView = chatView.findViewById(R.id.author)
    private val chatMessage: TextView = chatView.findViewById(R.id.message)
    private val chatVerified: ImageView = chatView.findViewById(R.id.verified_status)
    private val chatSendDate: TextView = chatView.findViewById(R.id.date_time)
    private val chatNewMessageCount: TextView = chatView.findViewById(R.id.new_messages_count)
    private val chatMessageStatus: ImageView = chatView.findViewById(R.id.send_status)
    private val chatMutedStatus: ImageView = chatView.findViewById(R.id.muted_status)

    fun bind(chat: Chat, listener: ChatAdapter.ChatClickListener) {
        chatAvatar.setBackgroundResource(chat.avatarImage)
        chatName.text = chat.dialogName
        chatAuthor.text = chat.messageAuthor
        chatMessage.text = chat.message
        chatMessage.visibility = if (chat.message.isNullOrEmpty()) View.GONE else View.VISIBLE
        chatVerified.visibility = if (chat.verified) View.VISIBLE else View.GONE
        chatSendDate.text = chat.sendDate
        chatNewMessageCount.text = chat.newMessageCount.toString()
        chatNewMessageCount.visibility = if (chat.newMessageCount > 0) View.VISIBLE else View.GONE
        chatMessageStatus.setBackgroundResource(chat.messageStatus.res)
        chatMutedStatus.visibility = if (chat.mutedStatus) View.VISIBLE else View.GONE

        chatView.setOnClickListener {
            listener.onChatClick(
                chat,
                adapterPosition
            )
        }
    }
}