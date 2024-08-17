package otus.gpb.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class ChatAdapter (private val items: MutableList<ChatItem>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view)
    }

    fun removeAt(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun onLoadMore(context: Context) {
        Toast.makeText(context, "Load more", Toast.LENGTH_LONG).show()
        val newChatItems = GenerateChatItems().getList(10)
        val getCountItems = items.size
        items.addAll(newChatItems)
        this.notifyItemRangeInserted(getCountItems, newChatItems.size)
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mainImg: ShapeableImageView = itemView.findViewById(R.id.image_icon)
        private val mainName: TextView = itemView.findViewById(R.id.text_view_name)
        private val bottomName: TextView = itemView.findViewById(R.id.text_view_name_bottom)
        private val imgMessage: ImageView = itemView.findViewById(R.id.img_message)
        private val message: TextView = itemView.findViewById(R.id.text_view_message)
        private val imgVerified: ImageView = itemView.findViewById(R.id.img_verified)
        private val imgMuted: ImageView = itemView.findViewById(R.id.img_noise)
        private val time: TextView = itemView.findViewById(R.id.text_view_time)
        private val imgFixed: ImageButton = itemView.findViewById(R.id.img_button_fixed)
        private val imgStateMessage: ImageView = itemView.findViewById(R.id.img_readed)

        fun bind(itemChat: ChatItem) {
            mainName.text = itemChat.mainName
            mainImg.setImageResource(itemChat.mainImg)
            message.text = itemChat.textMessage
            time.text = itemChat.textTime
            imgStateMessage.setImageResource(itemChat.stateMessage)

            if (itemChat.isVerified) {
                imgVerified.setImageResource(R.drawable.verif)
            }
            else {
                imgVerified.visibility = View.GONE
            }
            if (itemChat.isMuted) {
                imgMuted.setImageResource(R.drawable.muted)
            }
            if (itemChat.isFavour) {
                imgFixed.setImageResource(R.drawable.favour)
            }
            if (itemChat.secondaryName != null) {
                bottomName.text = itemChat.secondaryName
            }
            else {
                bottomName.visibility = View.GONE
            }
            if (itemChat.messageImg != null) {
                imgMessage.setImageResource(itemChat.messageImg)
            }
            else {
                imgMessage.visibility = View.GONE
            }
        }
    }


}