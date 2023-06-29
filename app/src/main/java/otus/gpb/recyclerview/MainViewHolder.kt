package otus.gpb.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.model.MainModel

class MainViewHolder (
    view: View,
) : RecyclerView.ViewHolder(view) {

    fun bind(model: MainModel) {
        itemView.findViewById<ImageView>(R.id.imageView_avatar).setImageResource(model.avatar)

        itemView.findViewById<ImageView>(R.id.imageView_preview).apply {
            if (model.preview == null)
                visibility = View.GONE
            else {
                visibility = View.VISIBLE
                setImageResource(model.preview)
            }
        }

        itemView.findViewById<ImageView>(R.id.imageView_check).apply {
            if (model.check == null)
                visibility = View.GONE
            else {
                visibility = View.VISIBLE
                setImageResource(model.check)
            }
        }

        itemView.findViewById<TextView>(R.id.textView_nick).apply {
            text = model.nick
        }
        itemView.findViewById<TextView>(R.id.textView_subLine).apply {
            text = model.status
        }
        itemView.findViewById<TextView>(R.id.textView_lastMas).apply {
            text = model.last_massage
        }
        itemView.findViewById<TextView>(R.id.textView_time).apply {
            text = model.time
        }

        itemView.findViewById<ImageView>(R.id.imageView_circle).apply {
            visibility = if (model.count == 0)
                View.GONE
            else {
                View.VISIBLE
            }
        }
        itemView.findViewById<TextView>(R.id.textView_count).apply {
            if (model.count == 0)
                visibility = View.GONE
            else {
                visibility = View.VISIBLE
                text = model.count.toString()
            }
        }

        itemView.findViewById<ImageView>(R.id.imageView_scam_patch).apply {
            visibility = if (model.isSCAM)
                View.VISIBLE
            else
                View.GONE
        }

        itemView.findViewById<ImageView>(R.id.imageView_mute_icon).apply {
            visibility = if (model.isMute)
                View.VISIBLE
            else
                View.GONE
        }

        itemView.findViewById<ImageView>(R.id.imageView_verified_icon).apply {
            visibility = if (model.isVerified)
                View.VISIBLE
            else
                View.GONE
        }
    }

}
