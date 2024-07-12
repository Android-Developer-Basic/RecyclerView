package otus.gpb.recyclerview

data class ChatItem(
    val name: String,
    val isMuted: Boolean,
    val isVerified: Boolean,
    val isScam: Boolean,
    val title: String,
    val message: String,
    val time: String,
    val image: Int
)