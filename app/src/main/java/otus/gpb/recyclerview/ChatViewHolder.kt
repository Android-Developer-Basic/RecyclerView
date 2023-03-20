package layout

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.ChatData
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatViewHolder(private val binding: ChatItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind (item: ChatData) {
        with(binding) {
            title.text = item.title
            messageAuthor.text = item.author
            messageText.text = item.message
            verifyImage.visibility = if(item.isVerify)  View.VISIBLE else View.GONE
            muteImage.visibility = if(item.isMute)  View.VISIBLE else View.GONE
            scamImage.visibility = if(item.isScam)  View.VISIBLE else View.GONE
            avatar.setImageResource(item.avatar)
            datetime.text= item.dataTime
            checkImage.visibility = if(item.isChecked && !item.isRead)  View.VISIBLE else View.GONE
            readImage.visibility = if(item.isRead)  View.VISIBLE else View.GONE
            counter.text = item.counter.toString()
        }
    }
}