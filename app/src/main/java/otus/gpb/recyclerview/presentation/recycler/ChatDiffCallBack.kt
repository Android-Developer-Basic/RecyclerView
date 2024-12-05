package otus.gpb.recyclerview.presentation.recycler

import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import otus.gpb.recyclerview.data.model.Chat

class ChatDiffCallBack() : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }

}