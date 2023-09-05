package otus.gpb.recyclerview

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import otus.gpb.recyclerview.databinding.ItemMessageBinding

class ChatViewHolder(private val binding: ItemMessageBinding, private val onClickListener: (Int) -> Unit) : ViewHolder(binding.root) {
    fun bind(model: ChatModel) {
        binding.imageViewAvatar.setImageResource(model.avatarImage)
        binding.imageViewAvatar.setOnClickListener {
            onClickListener.invoke(adapterPosition)//Тут нужен не номер в листе, а номер в адаптере, иначе получается Г
        }
        binding.imageViewPreview.setImageResource(model.previewImage)

        binding.textViewNickname.text = model.nickName
        binding.textViewMessage.text = model.message
        binding.textViewMessTime.text = model.lastTime
        binding.textViewMessCount.text = model.messCount.toString()

        if (model.group.isEmpty())
            binding.textViewGroup.visibility = View.GONE
        else
            binding.textViewGroup.text = model.group

        if (!model.check)
            binding.imageViewCheck.visibility = View.GONE

        if (!model.scam)
            binding.imageViewScam.visibility = View.GONE

        if (!model.mute)
            binding.imageViewMute.visibility = View.GONE

        if (!model.verified)
            binding.imageViewVerified.visibility = View.GONE
    }
}