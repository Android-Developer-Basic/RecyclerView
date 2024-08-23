package otus.gpb.recyclerview.model

import java.util.Date
import kotlin.random.Random

data class Message(
    val title: String,
    val hasImg: Boolean,
    val status: MessageStatusEnum,
    val date: Date
) {

    companion object {
        fun getRandom(): Message {
            return Message(
                title = listOf("BanG Bang", "Hello","Hi","Salut!").random(),
                Random.nextBoolean(),
                listOf(
                    MessageStatusEnum.READ,
                    MessageStatusEnum.DELIVERED,
                    MessageStatusEnum.SEND
                ).random(),
                listOf(
                    Date(),
                    Date(System.currentTimeMillis() + (0..1000).random() * 60 * 60 * 1000)
                ).random()
            )
        }
    }
}




