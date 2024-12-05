package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val avatar:ImageView by lazy { itemView.findViewById(R.id.avatar) }
    private val title:TextView by lazy { itemView.findViewById(R.id.title) }
    private val verified:ImageView by lazy { itemView.findViewById(R.id.verify) }
    private val mute:ImageView by lazy { itemView.findViewById(R.id.mute) }
    private val scam:ImageView by lazy { itemView.findViewById(R.id.scam) }
    private val messageAuthor:TextView by lazy { itemView.findViewById(R.id.messageAuthor) }
    private val preview:ImageView by lazy { itemView.findViewById(R.id.preview) }
    private val message:TextView by lazy { itemView.findViewById(R.id.message) }
    private val check:ImageView by lazy { itemView.findViewById(R.id.check) }
    private val read:ImageView by lazy { itemView.findViewById(R.id.read) }
    private val time:TextView by lazy { itemView.findViewById(R.id.time) }
    private val counter:ImageView by lazy { itemView.findViewById(R.id.counter) }

    fun bind(chat:Chat) {
        avatar.setImageResource(chat.avatar)
        title.text = chat.title
        verified.setImageResource(chat.verify)
        mute.setImageResource(chat.mute)
        scam.setImageResource(chat.scam)
        messageAuthor.text = chat.messageAuthor
        preview.setImageResource(chat.preview)
        message.text = chat.message
        check.setImageResource(chat.check)
        read.setImageResource(chat.read)
        time.text = chat.time
        counter.setImageResource(chat.counter)
    }
}