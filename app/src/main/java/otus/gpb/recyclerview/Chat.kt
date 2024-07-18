package otus.gpb.recyclerview

class Chat {
    fun fillData(): MutableList<Item> {
            return mutableListOf(
                Item(
                    avatar = R.drawable.avatar_pizza,
                    name = "Pizza",
                    message = "Yes,they are necessary",
                    comment = "jija",
                    date = "11.38 AM",
                    mute = true,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = true
                ),
                Item(
                    avatar = R.drawable.avatar_elon,
                    name = "Elon",
                    message = "I love /r/Reddit!",
                    comment = "",
                    date = "12.44 AM",
                    mute = true,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = false
                ),
                Item(
                    avatar = R.drawable.avatar_pasha,
                    name = "Pasha",
                    message = "How are u?",
                    comment = "",
                    date = "Fri",
                    mute = true,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = false
                ),
                Item(
                    avatar = R.drawable.kitty,
                    name = "Kitty",
                    message = "The time is now",
                    comment = "",
                    date = "Thu",
                    mute = true,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = true,
                    scum = false,
                    pictureInMessage = false
                ),

                Item(
                    avatar = R.drawable.avatar_telegram,
                    name = "Telegram Support",
                    message = "Yes it happened",
                    comment = "Support",
                    date = "Thu",
                    mute = false,
                    verify = true,
                    unreadMessages = 1,
                    check = false,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = false
                ),

                Item(
                    avatar = R.drawable.avatar_karina,
                    name = "Karina",
                    message = "Okey",
                    comment = "",
                    date = "Wed",
                    mute = false,
                    verify = false,
                    unreadMessages = 0,
                    check = true,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = false
                ),

                Item(
                    avatar = R.drawable.avatar_marilyn,
                    name = "Marilyn",
                    message = "Will it ever happen",
                    comment = "",
                    date = "May 02",
                    mute = false,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = true,
                    scum = true,
                    pictureInMessage = false
                ),
                Item(
                    avatar = R.drawable.avatar_pizza,
                    name = "Pizza",
                    message = "Yes,they are necessary",
                    comment = "jija",
                    date = "11.38 AM",
                    mute = true,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = false
                ),
                Item(
                    avatar = R.drawable.avatar_elon,
                    name = "Elon",
                    message = "I love r/r/Reddit!",
                    comment = "",
                    date = "12.44 AM",
                    mute = true,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = false
                ),
                Item(
                    avatar = R.drawable.avatar_pasha,
                    name = "Pasha",
                    message = "How are u?",
                    comment = "",
                    date = "Fri",
                    mute = true,
                    verify = false,
                    unreadMessages = 0,
                    check = false,
                    doubleCheck = false,
                    scum = false,
                    pictureInMessage = false
                )
            )
    }
}