package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val avatar: ImageView by lazy { itemView.findViewById(R.id.avatar) }
    private val name: TextView by lazy { itemView.findViewById(R.id.name) }
    private val message: TextView by lazy { itemView.findViewById(R.id.message) }
    private val messageAuthor: TextView by lazy { itemView.findViewById(R.id.messageAuthor) }
    private val messagePicture: ImageView by lazy { itemView.findViewById(R.id.messageIcon) }
    private val muteIcon: ImageView by lazy { itemView.findViewById(R.id.muteIcon) }
    private val date: TextView by lazy { itemView.findViewById(R.id.date) }
    private val verifyIcon: ImageView by lazy { itemView.findViewById(R.id.verifyIcon) }
    private val messageCountIcon: ImageView by lazy { itemView.findViewById(R.id.messageCountIcon) }
    private val messageReadIcon: ImageView by lazy { itemView.findViewById(R.id.readMessageIcon) }
    private val scamIcon: ImageView by lazy { itemView.findViewById(R.id.scamIcon) }

    fun bind(itemModel: ChatItem) {
        name.text = itemModel.name
        message.text = itemModel.message
        avatar.setImageResource(itemModel.avatar)
        messagePicture.setVisibility(itemModel.withMessagePicture)
        muteIcon.setVisibility(itemModel.withMuteIcon)
        verifyIcon.setVisibility(itemModel.withVerifyIcon)
        messageCountIcon.setVisibility(itemModel.withMessageCountIcon)
        messageReadIcon.setImageResource(itemModel.messageReadIcon)
        scamIcon.setVisibility(itemModel.isScam)
        if (itemModel.messageAuthor != "") {
            messageAuthor.text = itemModel.messageAuthor
            messageAuthor.visibility = View.VISIBLE
        } else {
            messageAuthor.visibility = View.GONE
        }
        date.text = itemModel.date
    }
}