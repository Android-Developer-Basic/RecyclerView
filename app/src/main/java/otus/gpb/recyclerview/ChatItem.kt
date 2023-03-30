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

    val unreadMessageCount: Int = 0
) {
    val onClickListener: ((item: ChatItem) -> Unit)? = null
}
