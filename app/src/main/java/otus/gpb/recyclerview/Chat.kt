package otus.gpb.recyclerview

data class Chat(
    val idAvatar: Int = R.drawable.common,
    val title: String = "",
    val subtitle: String = "",
    val message: String = "",
    val dateTimeText: String = "",
    val verified: Boolean = false,
    val muted: Boolean = false,
    val scum: Boolean = false,
    val sent: Boolean = false,
    val read: Boolean = false,
    val unreadMessages: Int = 0
)

fun getChatItems(): MutableList<Chat> = mutableListOf(
    Chat(
        idAvatar = R.drawable.pizza,
        title = "Pizza",
        subtitle = "jija",
        message = "Yes, they are necessary",
        dateTimeText = "11:38 AM",
        muted = true
    ),
    Chat(
        idAvatar = R.drawable.elon,
        title = "Elon",
        message = "I love /r/Reddit!",
        dateTimeText = "12:44 AM",
        muted = true
    ),
    Chat(
        idAvatar = R.drawable.pasha,
        title = "Pasha",
        message = "How are u?",
        dateTimeText = "Fri",
        muted = true,
        verified = true
    ),
    Chat(
        title = "Telegram Support",
        subtitle = "Support",
        message = "Yes it happened.",
        dateTimeText = "Thu",
        verified = true,
        unreadMessages = 1
    ),
    Chat(
        idAvatar = R.drawable.karina,
        title = "Karina",
        message = "Okay",
        dateTimeText = "Wed",
        sent = true
    ),
    Chat(
        idAvatar = R.drawable.marilyn,
        title = "Marylin",
        message = "Will it ever happen",
        dateTimeText = "May 02",
        scum = true,
        read = true
    ),
).apply {
    (7..60).forEach {
        add(
            Chat(
                title = "Item $it",
                message = "Yes, they are necessary",
                dateTimeText = "11:38 AM",
                muted = true
            )
        )
    }
}