package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.apache.commons.lang3.ClassUtils.getPackageName

class ChatTlgViewHolder( private val view : View ) : RecyclerView.ViewHolder( view ){

    private val image: ImageView    by lazy { view.findViewById(R.id.imageView) }
    private val chatName: TextView  by lazy { view.findViewById(R.id.chatNameTextView) }
    private val messAuthor: TextView by lazy { view.findViewById(R.id.authorTextView) }
    private val messageText: TextView by lazy { view.findViewById(R.id.messageTextView) }
    private val messageTime: TextView by lazy { view.findViewById(R.id.timeTextView) }
    private val messageCnt: TextView by lazy { view.findViewById(R.id.cntMessTextView) }


    fun bind( chatTlgItem: ChatTlgItem ) {

        chatName.text = chatTlgItem.chatName
        messAuthor.text = chatTlgItem.messageAuthor
        messageText.text = chatTlgItem.messageTitle
        messageTime.text = chatTlgItem.messageTime
        messageCnt.text = chatTlgItem.messageCnt.toString()

        image.setImageResource(R.drawable.ic_person)

        val resourceName = chatTlgItem.photo
        val res = view.context.getResources()
        val packageName = view.context.packageName
        val resourceId = res.getIdentifier(resourceName, "drawable", packageName )
        if (resourceId != 0) {

            image.setImageResource(resourceId)
            /*
            Glide.with(view.context)
                .load(resourceId)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL )
                .circleCrop()
                .into(image)
             */
        }
    }
}