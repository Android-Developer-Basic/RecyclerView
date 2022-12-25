package otus.gpb.recyclerview.model

data class Chat(
    val profileImage: Int,
    val name: String,
    val status: String,
    val isMuted: Boolean,
    val isVerified: Boolean,
    val lastMessage: String,
    val date: String,
    val messageCount: Int,
    val isDone: Boolean,
    val isDoneAll: Boolean
)
