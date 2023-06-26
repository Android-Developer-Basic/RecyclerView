package otus.gpb.recyclerview

data class ChatItem(
    val avatar: Int,
    val title: String,
    val verification: VerificationStatus = VerificationStatus.UNVERIFIED,
    val notification: NotificationStatus = NotificationStatus.UNMUTED,
    val author: String,
    val message: String,
    val messageStatus: MessageStatus = MessageStatus.UNDELIVERED,
    val dateTime: String,
    val messageCount: Int
){
    enum class VerificationStatus {
        UNVERIFIED,
        VERIFIED,
        SCAM
    }
    enum class NotificationStatus {
        UNMUTED,
        MUTED
    }
    enum class MessageStatus {
        UNREAD,
        READ,
        UNDELIVERED
    }
}