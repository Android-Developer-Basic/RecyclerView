package otus.gpb.recyclerview

class Chat(
    val avatar: Int = R.drawable.avatar_unknown,
    val name: String,
    val isVerify: Boolean = false,
    val isMute: Boolean = false,
    val isScam: Boolean = false,
    val author: String = "",
    val preview: Int? = null,
    val message: String = "",
    val isUnread: Boolean = false,
    val isRead: Boolean = false,
    val date: String,
    val counter: Int = 0,
) {
    companion object {
        private val pizza = Chat(
            avatar = R.drawable.avatar_pizza,
            name = "Pizza",
            isMute = true,
            preview = R.drawable.image,
            author = "jija",
            message = "Yes, they are necessary",
            date = "11:38 AM",
        )
        private val elon = Chat(
            avatar = R.drawable.avatar_elon,
            name = "Elon",
            isMute = true,
            message = "I love /r/Reddit!",
            date = "12:44 AM",
        )
        private val pasha = Chat(
            avatar = R.drawable.avatar_pasha,
            name = "Pasha",
            isVerify = true,
            isMute = true,
            message = "How are u?",
            date = "Fri",
        )
        private val unknown = Chat(
            name = "Unknown",
            message = "Yes, I know",
            isRead = true,
            date = "Thu",
        )
        private val telegram = Chat(
            avatar = R.drawable.avatar_telegram,
            name = "Telegram Support",
            isVerify = true,
            author = "Support",
            message = "Yes it happened.",
            date = "Thu",
            counter = 1,
        )
        private val karina = Chat(
            avatar = R.drawable.avatar_karina,
            name = "Karina",
            message = "Okay",
            isUnread = true,
            date = "Wed",
        )
        private val marilyn = Chat(
            avatar = R.drawable.avatar_marilyn,
            name = "Marilyn",
            isScam = true,
            message = "Will it ever happen",
            isRead = true,
            date = "May 02",
        )

        val chats = mutableListOf(
            pizza,
            elon,
            pasha,
            unknown,
            telegram,
            karina,
            marilyn
        )
    }
}