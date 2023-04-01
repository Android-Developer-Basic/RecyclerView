package otus.gpb.recyclerview

class ListGetter {

    fun getList(): MutableList<ChatItemData> {
        val list = mutableListOf<ChatItemData>(
            ChatItemData(
                avatar = R.drawable.avatar_1,
                name = "Pizza",
                author = "jija",
                message = "Yes, they are necessary",
                time = "11:38 AM",
                isMute = true
            ), ChatItemData(
                avatar = R.drawable.avatar_2,
                name = "Elon",
                message = "I love/r/Reddit!",
                time = "12:44 AM",
                isMute = true
            ), ChatItemData(
                avatar = R.drawable.avatar_3,
                name = "Pasha",
                message = "How are u?",
                time = "Fri",
                isVerified = true,
                isMute = true
            ), ChatItemData(
                avatar = R.drawable.avatar_4,
                name = "Telegram Support",
                author = "Support",
                message = "Yes it happend.",
                time = "Thu",
                isVerified = true,
                cont = 1
            ), ChatItemData(
                avatar = R.drawable.avatar_5,
                name = "Karina",
                message = "Okay",
                time = "Wed",
                isCheck = true
            ), ChatItemData(
                avatar = R.drawable.avatar_6,
                name = "Marlin",
                message = "Will it never happen",
                time = "May 02",
                isScam = true,
                isRead = true
            )
        )
        return list
    }
}