package otus.gpb.recyclerview

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding

class ChatHolder (item: View, private val context: Context): RecyclerView.ViewHolder(item) {
    private val binding = ChatItemBinding.bind(item)
    fun bind(chat: Chat) = with(binding){
        clearIcons()
        if (chat.userAvatar != null) avatarContainer.setImageResource(chat.userAvatar)
        nik.text = chat.userName
        message.text = chat.message
        time.text = chat.time
        chat.markers.forEachIndexed { index, b ->
            if(b) createIcons(index)
        }




    }
    private fun clearIcons(){
        binding.iconLayout.removeAllViews()

    }
    private fun createIcons(marker: Int){
        when(marker){
            0 -> {
                val verifyIV = ImageView(context)
                verifyIV.setImageResource(R.drawable.verify_account)
                binding.iconLayout.addView(verifyIV)
            }

            1 -> {
                val muteIV = ImageView(context)
                muteIV.setImageResource(R.drawable.mute)
                binding.iconLayout.addView(muteIV)
            }

            2 -> {
                binding.deliveryIcon.setImageResource(R.drawable.delivered)
            }

            3 -> {
                binding.deliveryIcon.setImageResource(R.drawable.viewed)
            }

            4 ->{
                binding.notOpenedMessageIcon.background =
                    AppCompatResources.getDrawable(context, R.drawable.not_opened_message)
                binding.notOpenedMessageCounter.text = "${(Math.random()*5).toInt()}"
            }

            5 ->{
                val scamIV = ImageView(context)
                scamIV.setImageResource(R.drawable.scam)
                binding.iconLayout.addView(scamIV)
            }

            else -> return
        }
    }

}
