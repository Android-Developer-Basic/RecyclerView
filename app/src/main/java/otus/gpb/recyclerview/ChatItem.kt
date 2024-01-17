package otus.gpb.recyclerview

data class ChatItem(
    val idAvatar: Int = R.drawable.common,
    val title: String = "",
    val subtitle: String = "",
    val text: String = "",
    val dateTimeText: String = "",
    val verified: Boolean = false,
    val muted: Boolean = false,
    val scum: Boolean = false,
    val sent: Boolean = false,
    val read: Boolean = false,
    val unreadMessages: Int = 0
)

fun getChatItems(): MutableList<ChatItem> = mutableListOf(
    ChatItem(
        idAvatar = R.drawable.pizza,
        title = "Pizza",
        subtitle = "jija",
        text = "Yes, they are necessary",
        dateTimeText = "11:38 AM",
        muted = true
    ),
    ChatItem(
        idAvatar = R.drawable.elon,
        title = "Elon",
        text = "I love /r/Reddit!",
        dateTimeText = "12:44 AM",
        muted = true
    ),
    ChatItem(
        idAvatar = R.drawable.pasha,
        title = "Pasha",
        text = "How are u?",
        dateTimeText = "Fri",
        muted = true,
        verified = true
    ),
    ChatItem(
        title = "Telegram Support",
        subtitle = "Support",
        text = "Yes it happened.",
        dateTimeText = "Thu",
        verified = true,
        unreadMessages = 1
    ),
    ChatItem(
        idAvatar = R.drawable.karina,
        title = "Karina",
        text = "Okay",
        dateTimeText = "Wed",
        sent = true
    ),
    ChatItem(
        idAvatar = R.drawable.marilyn,
        title = "Marylin",
        text = "Will it ever happen",
        dateTimeText = "May 02",
        scum = true,
        read = true
    ),
).apply {
    (7..60).forEach {
        add(
            ChatItem(
                title = "Item $it",
                text = "Yes, they are necessary",
                dateTimeText = "11:38 AM",
                muted = true
            )
        )
    }
}