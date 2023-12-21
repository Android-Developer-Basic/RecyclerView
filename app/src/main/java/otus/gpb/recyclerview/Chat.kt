package otus.gpb.recyclerview

data class Chat(
    val title: String,
    val subtitle: String = "",
    val text: String,
    val dateTimeText: String,
    val verified: Boolean = false,
    val muted: Boolean = false,
    val sent: Boolean = false,
    val read: Boolean = false,
    val unreadMessages: Int = 0
)
