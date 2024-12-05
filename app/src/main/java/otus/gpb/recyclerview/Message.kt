package otus.gpb.recyclerview

data class Message(
        val name: String,
        val subtitle: String = "",
        val message: String,
        val avatar: Int,
        val dateTimeText: String,
        val isVerified: Boolean = false,
        val isMuted: Boolean = false,
        val isSent: Boolean = false,
        val isRead: Boolean = false,
        val unreadCounter: Int? = null)
