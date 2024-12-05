package otus.gpb.recyclerview

import android.app.Application

class ChatApp: Application() {
    private val items = mutableListOf(
        ChatItem(
            avatar = R.drawable.pizza,
            title = "Pizza",
            verification = ChatItem.VerificationStatus.UNVERIFIED,
            notification = ChatItem.NotificationStatus.MUTED,
            author = "jija",
            message = "Yes, they are necessary",
            messageStatus = ChatItem.MessageStatus.UNDELIVERED,
            dateTime = "11:38 AM",
            messageCount = 0),
        ChatItem(
            avatar = R.drawable.elon,
            title = "Elon",
            verification = ChatItem.VerificationStatus.UNVERIFIED,
            notification = ChatItem.NotificationStatus.MUTED,
            author = "",
            message = "I love /r/Reddit!",
            messageStatus = ChatItem.MessageStatus.UNDELIVERED,
            dateTime = "12:44 AM",
            messageCount = 0),
        ChatItem(
            avatar = R.drawable.pasha,
            title = "Pasha",
            verification = ChatItem.VerificationStatus.VERIFIED,
            notification = ChatItem.NotificationStatus.MUTED,
            author = "",
            message = "How are u?",
            messageStatus = ChatItem.MessageStatus.UNDELIVERED,
            dateTime = "Fri",
            messageCount = 0),
        ChatItem(
            avatar = R.drawable.telegram,
            title = "Telegram Support",
            verification = ChatItem.VerificationStatus.VERIFIED,
            notification = ChatItem.NotificationStatus.UNMUTED,
            author = "Support",
            message = "Yes it happened.",
            messageStatus = ChatItem.MessageStatus.UNDELIVERED,
            dateTime = "Thu",
            messageCount = 1),
        ChatItem(
            avatar = R.drawable.karina,
            title = "Karina",
            verification = ChatItem.VerificationStatus.UNVERIFIED,
            notification = ChatItem.NotificationStatus.UNMUTED,
            author = "",
            message = "Okay",
            messageStatus = ChatItem.MessageStatus.UNREAD,
            dateTime = "Wed",
            messageCount = 0),
        ChatItem(
            avatar = R.drawable.marilyn,
            title = "Marilyn",
            verification = ChatItem.VerificationStatus.SCAM,
            notification = ChatItem.NotificationStatus.UNMUTED,
            author = "",
            message = "Will it ever happen",
            messageStatus = ChatItem.MessageStatus.READ,
            dateTime = "May 02",
            messageCount = 0)
    )

    fun getItems(): MutableList<ChatItem> {
        return items
    }
}