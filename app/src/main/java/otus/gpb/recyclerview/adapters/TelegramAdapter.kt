package otus.gpb.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.model.TelegramChatMessage
import otus.gpb.recyclerview.databinding.ItemCellBinding
import otus.gpb.recyclerview.utils.RecyclerViewListComparator

class TelegramAdapter: RecyclerView.Adapter<TelegramAdapter.ItemCell>() {

    private var telegramMessageList = ArrayList<TelegramChatMessage>()

    fun addItems(list: List<TelegramChatMessage>) {
        telegramMessageList.addAll(list)
        val result = DiffUtil.calculateDiff(RecyclerViewListComparator(telegramMessageList, list))
        result.dispatchUpdatesTo(this)
    }

    fun addItem(item: TelegramChatMessage) {
        telegramMessageList.add(item)
        notifyItemInserted(telegramMessageList.size + 1)
    }

    fun removeItem(position: Int) {
        telegramMessageList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCell {
        val binding = ItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCell(binding)
    }

    override fun onBindViewHolder(holder: ItemCell, position: Int) {
        holder.bind(telegramMessageList[position])
    }

    override fun getItemCount(): Int {
        return telegramMessageList.size
    }

    class ItemCell(private val binding: ItemCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TelegramChatMessage) {
            // TODO bind image
            binding.text.text = item.text
            binding.title.text = item.title
            binding.timeValue.text = item.date
        }

    }
}