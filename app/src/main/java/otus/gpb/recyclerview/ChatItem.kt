package otus.gpb.recyclerview

data class ChatItem(
    val name: String,
    val isMuted: Boolean,
    val isVerified: Boolean,
    val isScam: Boolean,
    val hasPrevPic: Boolean,
    val messageState: MessageState,
    val title: String?,
    val message: String,
    val time: String,
    val messageCounter: Int?,
    val image: Int
)