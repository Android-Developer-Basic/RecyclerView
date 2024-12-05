package otus.gpb.recyclerview

data class ChatItem(
    val avatar: Int,
    val name: String,
    val message: String,
    val messageAuthor: String = "",
    val withMessagePicture: Boolean = false,
    val withMuteIcon: Boolean = false,
    val date: String,
    val withVerifyIcon: Boolean = false,
    val withMessageCountIcon: Boolean = false,
    val messageReadIcon: Int,
    val isScam: Boolean = false
)

