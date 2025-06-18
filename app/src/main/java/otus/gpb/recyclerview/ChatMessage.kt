package otus.gpb.recyclerview

import java.sql.Date
import java.time.LocalDate

data class ChatMessage(
    val id: Int,
    val user: String,
    val message: String,
    val date: LocalDate
)

val chatMessages = listOf(
    ChatListItem.ChatItem(ChatMessage(0, "Dave", message = "Hello everyone!", date = LocalDate.now())),
    ChatListItem.ChatItem(ChatMessage(1, "Ana", message = "I want pizza for lunch", date = LocalDate.now().plusDays(1))),
    ChatListItem.ChatItem(ChatMessage(2, "Micky", message = "TDS helps you create your own concepts and learn new things", date = LocalDate.now().plusDays(2))),
    ChatListItem.ChatItem(ChatMessage(3, "Sarah", message = "Has anyone finished the project yet?", date = LocalDate.now().minusDays(1))),
    ChatListItem.ChatItem(ChatMessage(4, "John", message = "Meeting at 3 PM tomorrow", date = LocalDate.now().plusDays(3))),
    ChatListItem.ChatItem(ChatMessage(5, "Emma", message = "Don't forget to submit your reports", date = LocalDate.now().plusDays(1))),
    ChatListItem.ChatItem(ChatMessage(6, "Mike", message = "The new update is amazing!", date = LocalDate.now().minusDays(2))),
    ChatListItem.ChatItem(ChatMessage(7, "Lisa", message = "Can someone help me with Kotlin?", date = LocalDate.now())),
    ChatListItem.ChatItem(ChatMessage(8, "Alex", message = "Let's schedule a team building event", date = LocalDate.now().plusDays(5))),
    ChatListItem.ChatItem(ChatMessage(9, "Tina", message = "Happy Friday everyone!", date = LocalDate.now().plusDays(4)))
)