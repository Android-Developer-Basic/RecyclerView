package otus.gpb.recyclerview.utils

import androidx.recyclerview.widget.DiffUtil
import otus.gpb.recyclerview.model.TelegramChatMessage

class RecyclerViewListComparator(
    private val oldList: List<TelegramChatMessage>,
    private val newList: List<TelegramChatMessage>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}