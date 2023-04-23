package otus.gpb.recyclerview

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ItemRecyclerViewBinding

class ItemViewHolder(private val binding: ItemRecyclerViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(itemData: ItemData) {
        binding.itemName.text = itemData.name
        binding.itemCaption.text = itemData.caption
        binding.itemMessage.text = itemData.message
        binding.itemTime.text = itemData.time

        binding.itemCaption.isVisible = itemData.caption != null
        binding.verified.isVisible = itemData.isVerified
        binding.mute.isVisible = itemData.isMute
        binding.scam.isVisible = itemData.isScam
        binding.checked.isVisible = itemData.isChecked || itemData.isRead
        binding.checked.setImageResource(R.drawable.check)
        if (itemData.isRead) {
            binding.checked.setImageResource(R.drawable.read)
        }
        binding.avatar.setImageResource(itemData.avatar)
        binding.countText.isVisible = itemData.count != null
        binding.count.isVisible = itemData.count != null
        if (itemData.count != null) {
            binding.countText.text = itemData.count.toString()
        }

    }

    fun getSwipedView(): ConstraintLayout? {
        return binding.mainLayout
    }
}