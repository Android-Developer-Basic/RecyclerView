package otus.gpb.recyclerview

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import otus.gpb.recyclerview.models.ChatItem
import otus.gpb.recyclerview.models.ChatStatus
import otus.gpb.recyclerview.models.LastMessage
import java.time.LocalDateTime
import java.util.Date
import kotlin.random.Random

class MainViewModel: ViewModel() {
    var chats = MutableLiveData<List<ChatItem>>()
    fun loadData() {
        var result: MutableList<ChatItem> = emptyList<ChatItem>().toMutableList()
        val currentTime = System.currentTimeMillis()
        val hourInMillis = 60 * 60 * 1000
        result.add(
            ChatItem(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = generateImage(),
                title = "Pizza",
                subject = "jija",
                isMuted = Random.nextBoolean(),
                lastMessage = LastMessage(title = "Yes, they are necessary", hasImg = true),
                unReadCount = generateUnread(),
                isMessageSend = Random.nextBoolean(),
                isMessageRead = Random.nextBoolean(),
                date = Date(currentTime),
                status = generateStatus()
            )
        )

        result.add(
            ChatItem(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = generateImage(),
                title = "Elon",
                subject = "I love /r/Reddit!",
                isMuted = Random.nextBoolean(),
                lastMessage = LastMessage(title = "Yes, they are necessary", hasImg = true),
                unReadCount = generateUnread(),
                isMessageSend = Random.nextBoolean(),
                isMessageRead = Random.nextBoolean(),
                date = Date(currentTime - hourInMillis),
                status = generateStatus()
            )
        )

        result.add(
            ChatItem(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = generateImage(),
                title = "Elon",
                subject = "",
                isMuted = Random.nextBoolean(),
                lastMessage = LastMessage(title = "I love /r/Reddit!", hasImg = false),
                unReadCount = generateUnread(),
                isMessageSend = Random.nextBoolean(),
                isMessageRead = Random.nextBoolean(),
                date = Date(currentTime - hourInMillis * 24),
                status = generateStatus()
            )
        )

        result.add(
            ChatItem(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = generateImage(),
                title = "Pasha",
                subject = "",
                isMuted = Random.nextBoolean(),
                lastMessage = LastMessage(title = "How are u?", hasImg = false),
                unReadCount = generateUnread(),
                isMessageSend = Random.nextBoolean(),
                isMessageRead = Random.nextBoolean(),
                date = Date(currentTime - hourInMillis * 24),
                status = generateStatus()
            )
        )

        result.add(
            ChatItem(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = generateImage(),
                title = "Telegram Support",
                subject = "Support",
                isMuted = Random.nextBoolean(),
                lastMessage = LastMessage(title = "Yes, they are necessary", hasImg = false),
                unReadCount = generateUnread(),
                isMessageSend = Random.nextBoolean(),
                isMessageRead = Random.nextBoolean(),
                date = Date(currentTime - hourInMillis * 24),
                status = generateStatus()
            )
        )

        result.add(
            ChatItem(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = generateImage(),
                title = "Karina",
                subject = "",
                isMuted = Random.nextBoolean(),
                lastMessage = LastMessage(title = "Okay", hasImg = false),
                unReadCount = generateUnread(),
                isMessageSend = Random.nextBoolean(),
                isMessageRead = Random.nextBoolean(),
                date = Date(currentTime - hourInMillis * 24),
                status = generateStatus()
            )
        )

        result.add(
            ChatItem(
                id = java.util.UUID.randomUUID().toString(),
                imgRes = generateImage(),
                title = "Marilyn",
                subject = "",
                isMuted = Random.nextBoolean(),
                lastMessage = LastMessage(title = "Will it ever happen", hasImg = false),
                unReadCount = generateUnread(),
                isMessageSend = Random.nextBoolean(),
                isMessageRead = Random.nextBoolean(),
                date = Date(currentTime - hourInMillis * 24),
                status = generateStatus()
            )
        )

        chats.value = result

    }

    @DrawableRes
    fun generateImage(): Int {
        val rnds = (0..10).random()
        if (rnds % 5 == 0) {
            return R.drawable.avatar1
        } else if(rnds % 5 == 1) {
            return R.drawable.avatar2
        } else if(rnds % 5 == 2) {
            return R.drawable.avatar3
        } else if(rnds % 5 == 3) {
            return R.drawable.avatar4
        } else {
            return R.drawable.avatar5
        }
    }

    fun generateStatus(): ChatStatus {
        val rnds = (0..10).random()
        if (rnds % 5 == 0) {
            return ChatStatus.Scam
        } else if (rnds % 5 == 1) {
            return ChatStatus.Progress
        }

        return ChatStatus.None
    }

    fun generateUnread(): Int {
        return (0..5).random()
    }
}