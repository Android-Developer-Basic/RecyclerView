package otus.gpb.recyclerview

import androidx.annotation.LayoutRes
import java.time.LocalDate

sealed class ChatListItem: WithLayoutId {
    data class ChatItem(private val chatMessage: ChatMessage) : ChatListItem(), WithLayoutId by ChatItem  {
        companion object : WithLayoutId {
            @get:LayoutRes
            override val layoutId: Int = R.layout.chat_item
        }

        val user: String get() = chatMessage.user
        val message: String get() = chatMessage.message
        val date: LocalDate get() = chatMessage.date
    }

    data class LoadItem(private val id: String = "1") : ChatListItem(), WithLayoutId by LoadItem {
        companion object : WithLayoutId {
            @get:LayoutRes
            override val layoutId: Int = R.layout.load_item
        }
    }
}

