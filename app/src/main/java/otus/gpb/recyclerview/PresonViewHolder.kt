package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatViewHolder (
    private val view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView by lazy { view.findViewById(R.id.nameTV) }
        private val title: TextView by lazy { view.findViewById(R.id.titleTV) }
        private val message: TextView by lazy { view.findViewById(R.id.messageTV) }
        private val time: TextView by lazy { view.findViewById(R.id.timeTV) }
        private val image: ImageView by lazy { view.findViewById(R.id.imageAvatar) }
        fun bind(item: Chat){
            name.text = item.name
            title.text = item.title
            message.text = item.message
            time.text = item.time
            image.setImageResource(item.image)

        }


}