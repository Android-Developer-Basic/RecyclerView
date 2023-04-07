package otus.gpb.recyclerview

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatHolder (item: View, private val context: Context): RecyclerView.ViewHolder(item) {
    private val binding = ChatItemBinding.bind(item)
    fun bind(chat: Chat) = with(binding){
        clearViews()
        if (chat.userAvatar != null) avatarContainer.setImageResource(chat.userAvatar)
        nik.text = chat.userName
        message.text = chat.message
        time.text = chat.time
        var isFirstIconEmpty = true
        chat.flags.forEach { flag ->
            when(flag){
                "VERIFY_ACCOUNT" -> {

                    binding.icon1.setImageResource(R.drawable.verify_account)
                    isFirstIconEmpty = false
                }

                "MUTE" -> {
                    if(!isFirstIconEmpty) binding.icon2.setImageResource(R.drawable.mute)
                    else binding.icon1.setImageResource(R.drawable.mute)
                }

                "DELIVERED" -> {
                    binding.deliveryIcon.setImageResource(R.drawable.delivered)
                }

                "VIEWED" -> {
                    binding.deliveryIcon.setImageResource(R.drawable.viewed)
                }

                "NOT_OPENED" ->{
                    binding.notOpenedMessageIcon.background =
                        ContextCompat.getDrawable(context, R.drawable.not_opened_message)
                    chat.numberOfMessages?.let {
                        binding.notOpenedMessageCounter.text = "$it"
                    }
                }

                "SCAM" ->{
                    binding.icon1.setImageResource(R.drawable.scam)
                    isFirstIconEmpty = false
                }

                else -> return
            }

        }

    }

    private fun clearViews(){
        with(binding) {
            icon1.setImageResource(0)
            icon2.setImageResource(0)
            deliveryIcon.setImageResource(0)
            notOpenedMessageIcon.background = null
            notOpenedMessageCounter.text = ""
        }
    }

}
