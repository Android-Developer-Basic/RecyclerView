package otus.gpb.recyclerview

import android.app.Application

class App : Application(){
    val chats = mutableListOf(
        Chat( avatarImage = R.drawable.avatar_0, dialogName = "Gordon Carrillo", messageAuthor = "", message = "Never gonna give you up", verified = false, sendDate = "12:13", newMessageCount = 1, messageStatus = Status.UNREAD, mutedStatus = true),
        Chat( avatarImage = R.drawable.avatar_1, dialogName = "Annika Parks", messageAuthor = "", message = "Never gonna let you down", verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.DELIVERED, mutedStatus = false),
        Chat( avatarImage = R.drawable.avatar_2, dialogName = "Aya Romero", messageAuthor = "", message = "Never gonna run around and",verified = true, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.READ, mutedStatus = false),
        Chat( avatarImage = R.drawable.avatar_3, dialogName = "Clara Avila", messageAuthor = "You", message = "desert you", verified = false, sendDate = "12:13", newMessageCount = 5, messageStatus = Status.READ, mutedStatus = false),
        Chat( avatarImage = R.drawable.avatar_4, dialogName = "Eryn Burke", messageAuthor = "", message = "Never gonna make you cry", verified = true, sendDate = "12:13", newMessageCount = 5, messageStatus = Status.UNREAD, mutedStatus = true),
        Chat( avatarImage = R.drawable.avatar_5, dialogName = "Jasmine Wright", messageAuthor = "", message = "Never gonna say goodbye",verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.UNREAD, mutedStatus = false),
        Chat( avatarImage = R.drawable.avatar_6, dialogName = "Nathanael Mclean", messageAuthor = "", message = "Never gonna tell a lie", verified = false, sendDate = "12:13", newMessageCount = 2, messageStatus = Status.READ, mutedStatus = true),
        Chat( avatarImage = R.drawable.avatar_7, dialogName = "Jamie Harper", messageAuthor = "You", message = "and hurt you", verified = true, sendDate = "12:13", newMessageCount = 5, messageStatus = Status.UNREAD, mutedStatus = false),
        Chat( avatarImage = R.drawable.avatar_8, dialogName = "Craig Hartley", messageAuthor = "", message = "", verified = false, sendDate = "12:13", newMessageCount = 10, messageStatus = Status.DELIVERED, mutedStatus = true),
        Chat( avatarImage = R.drawable.avatar_9, dialogName = "Marcus Roth", messageAuthor = "", message = " ", verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.UNREAD, mutedStatus = false),
        Chat( avatarImage = R.drawable.avatar_10, dialogName = "Anna Webster", messageAuthor = "", message = "", verified = false, sendDate = "12:13", newMessageCount = 4, messageStatus = Status.READ, mutedStatus = false),
    )

    val additionalChats = mutableListOf(
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 1", messageAuthor = "Author", message = "New message",  verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.READ, mutedStatus = true),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 2 ", messageAuthor = "Author", message = "New message", verified = true, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.DELIVERED, mutedStatus = false),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 3", messageAuthor = "Author", message = "New message", verified = true, sendDate = "12:13", newMessageCount = 2, messageStatus = Status.READ, mutedStatus = true),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 4", messageAuthor = "Author", message = "New message", verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.UNREAD, mutedStatus = false),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 5", messageAuthor = "Author", message = "New message", verified = true, sendDate = "12:13", newMessageCount = 11, messageStatus = Status.DELIVERED, mutedStatus = false),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 6", messageAuthor = "Author", message = "New message", verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.READ, mutedStatus = false),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 7", messageAuthor = "Author", message = "New message", verified = false, sendDate = "12:13", newMessageCount = 5, messageStatus = Status.DELIVERED, mutedStatus = true),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 8", messageAuthor = "Author", message = "New message", verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.READ, mutedStatus = false),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 9", messageAuthor = "Author", message = "New message", verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.UNREAD, mutedStatus = false),
        Chat( avatarImage = R.drawable.default_user_avatar, dialogName = "New chat 10", messageAuthor = "Author", message = "New message", verified = false, sendDate = "12:13", newMessageCount = 0, messageStatus = Status.UNREAD, mutedStatus = true)
    )
}