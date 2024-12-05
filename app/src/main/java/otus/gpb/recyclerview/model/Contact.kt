package otus.gpb.recyclerview.model

import androidx.annotation.DrawableRes
import otus.gpb.recyclerview.R
import kotlin.random.Random

data class Contact(
    val id: String,
    @DrawableRes val imgRes: Int,
    val title: String,
    val subject: String,
    val isMuted: Boolean,
    val message: Message?,
    val unReadCount: Int,
    val isScum: Boolean,
){
    companion object {
        fun getRandom(): Contact {
            return Contact(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = R.drawable.avatar,
                title = listOf("Very", "bad", "title").random(),
                subject = listOf("John", "Mary", "Beaver").random()
                    .plus(" ")
                    .plus(listOf("Smith", "Telegram", "Apple").random()),
                isMuted = Random.nextBoolean(),
                message = Message.getRandom(),
                unReadCount = (0..1000).random(),
                isScum = Random.nextBoolean()
            )
        }
    }
}
