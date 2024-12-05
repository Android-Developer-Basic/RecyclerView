package otus.gpb.recyclerview

class Chat(
    val id: Int = 0,
    val avatarImage: Int ,
    val previewImage: Int? ,
    val nickName: String = "NickName",
    val group: String = "",
    val message: String = "",
    val lastTime: String = "Sun",
    val messCount: Int = 0,
    val check: Boolean = false,
    val isViewed: Boolean = false,
    val scam: Boolean = false,
    val mute: Boolean = false,
    val verified: Boolean = false
) {
    companion object {
        val pizza = Chat(
            id = 1,
            avatarImage = R.drawable.pizza_avatar,
            previewImage = R.drawable.preview,
            nickName = "Pizza",
            group = "jija",
            message = "Yes, they are necessary",
            lastTime = "11:38 AM",
            mute = true
        )
        val elon = Chat(
            id = 2,
            avatarImage = R.drawable.ilon_avatar,
            previewImage = null,
            nickName = "Elon",
            message = "I love /r/Reddit!",
            lastTime = "12:44 AM",
            mute = true
        )
        val pasha = Chat(
            id = 3,
            avatarImage = R.drawable.pasha_avatar,
            previewImage = null,
            nickName = "Pasha",
            message = "How are u?",
            lastTime = "Fri",
            mute = true,
            verified = true
        )
        val pasha2 = Chat(
            id = 4,
            avatarImage = R.drawable.pasha_avatar,
            previewImage = null,
            nickName = "Pasha2",
            message = "what are u doing now",
            lastTime = "Thu",
            isViewed = true,
            mute = true
        )
        val telegramSupport = Chat(
            id = 5,
            avatarImage = R.drawable.tg_avatar,
            previewImage = null,
            nickName = "Telegram Support",
            group = "Support",
            message = "Yes it happened.",
            lastTime = "Thu",
            messCount = 1,
            verified = true
        )
        val karina = Chat(
            id = 6,
            avatarImage = R.drawable.karina_avatar,
            previewImage = null,
            nickName = "Karina",
            message = "Okay",
            lastTime = "Wed",
            check = true
        )
        val marilyn = Chat(
            id = 7,
            avatarImage = R.drawable.marlin_avatar,
            previewImage = null,
            nickName = "Marilyn",
            message = "Will it ever happen",
            lastTime = "May 02",
            isViewed = true,
            scam = true
        )
        val chatList = mutableListOf(
            pizza,
            elon,
            pasha,
            pasha2,
            telegramSupport,
            karina,
            marilyn
        )
    }
}












