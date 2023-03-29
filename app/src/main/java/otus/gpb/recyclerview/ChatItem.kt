package otus.gpb.recyclerview

data class ChatItem(
    val id: Int,
    val userName: String,
    val lastMessage: String,
    val date: String,
    val avatar: String? = null,

    val isMuted: Boolean = false,
    val isVerified: Boolean = false,
    val isScam: Boolean = false,

    val isLastMessageMine: Boolean = false,
    val isMessageDelivered: Boolean = false,
    val isMessageRead:Boolean = false,

    val hasUnreadMessage: Boolean = false
)
