package otus.gpb.recyclerview

class Contacts {
    fun getList():MutableList<Chat>{
        return mutableListOf(
            Chat(
                avatar = R.drawable.image_pizza,
                title = "Pizza",
                verify = 0,
                mute = R.drawable.mute_icon,
                scam = 0,
                messageAuthor = "jija",
                preview = R.drawable.icon_preview,
                message = "Yes, they are necessary",
                check = 0,
                read = 0,
                time = "11:38 AM",
                counter = 0
            ),
            Chat(
                avatar = R.drawable.image_elon,
                title = "Elon",
                verify = 0,
                mute = R.drawable.mute_icon,
                scam = 0,
                messageAuthor = "",
                preview = 0,
                message = "I love /r/Reddit!",
                check = 0,
                read = 0,
                time = "12:44 AM",
                counter = 0
            ),
            Chat(
                avatar = R.drawable.image_pasha,
                title = "Pasha",
                verify = R.drawable.verified_icon,
                mute = R.drawable.mute_icon,
                scam = 0,
                messageAuthor = "",
                preview = 0,
                message = "How are u?",
                check = 0,
                read = 0,
                time = "Fri",
                counter = 0
            ),
            Chat(
                avatar = R.drawable.image_telegram,
                title = "Telegram",
                verify = R.drawable.verified_icon,
                mute = 0,
                scam = 0,
                messageAuthor = "Support",
                preview = R.drawable.icon_preview,
                message = "Yes it happened.",
                check = 0,
                read = 0,
                time = "Thu",
                counter = R.drawable.counter_icon
            ),
            Chat(
                avatar = R.drawable.image_karina,
                title = "Karina",
                verify = 0,
                mute = 0,
                scam = 0,
                messageAuthor = "",
                preview = 0,
                message = "Okay",
                check = 0,
                read = R.drawable.read_icon,
                time = "Wed",
                counter = 0
            ),
            Chat(
                avatar = R.drawable.image_marilyn,
                title = "Marilyn",
                verify = 0,
                mute = 0,
                scam = R.drawable.scam_icon,
                messageAuthor = "",
                preview = 0,
                message = "Will it ever happen",
                check = R.drawable.check_icon,
                read = 0,
                time = "May 02",
                counter = 0
            )
        )
    }
}