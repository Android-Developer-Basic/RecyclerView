package otus.prokofev.recyclerview

import android.app.Application

class Application : Application() {
    val chats = mutableListOf(
        Chat(
            avatarImage = R.drawable.avatar_0,
            dialogName = "Gordon Carrillo",
            messageAuthor = "",
            message = "Gordon Carrillo message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 1,
            messageStatus = Status.UNREAD,
            mutedStatus = true
        ),
        Chat(
            avatarImage = R.drawable.avatar_1,
            dialogName = "Annika Parks",
            messageAuthor = "",
            message = "Annika Parks message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.DELIVERED,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.avatar_2,
            dialogName = "Aya Romero",
            messageAuthor = "",
            message = "Aya Romero message",
            verified = true,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.READ,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.avatar_3,
            dialogName = "Clara Avila",
            messageAuthor = "You",
            message = "Clara Avila message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 5,
            messageStatus = Status.READ,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.avatar_4,
            dialogName = "Eryn Burke",
            messageAuthor = "",
            message = "Eryn Burke message",
            verified = true,
            sendDate = "12:13",
            newMessageCount = 5,
            messageStatus = Status.UNREAD,
            mutedStatus = true
        ),
        Chat(
            avatarImage = R.drawable.avatar_5,
            dialogName = "Jasmine Wright",
            messageAuthor = "",
            message = "Jasmine Wright message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.SENDING,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.avatar_6,
            dialogName = "Nathanael Mclean",
            messageAuthor = "",
            message = "Nathanael Mclean message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 2,
            messageStatus = Status.READ,
            mutedStatus = true
        ),
        Chat(
            avatarImage = R.drawable.avatar_7,
            dialogName = "Jamie Harper",
            messageAuthor = "You",
            message = "Jamie Harper message",
            verified = true,
            sendDate = "12:13",
            newMessageCount = 5,
            messageStatus = Status.UNREAD,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.avatar_8,
            dialogName = "Craig Hartley",
            messageAuthor = "",
            message = "",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 10,
            messageStatus = Status.DELIVERED,
            mutedStatus = true
        ),
        Chat(
            avatarImage = R.drawable.avatar_9,
            dialogName = "Marcus Roth",
            messageAuthor = "",
            message = " ",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.UNREAD,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.avatar_10,
            dialogName = "Anna Webster",
            messageAuthor = "",
            message = "message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 4,
            messageStatus = Status.SENDING,
            mutedStatus = false
        ),
    )

    val indefiniteChats = mutableListOf(
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 1",
            messageAuthor = "Author",
            message = "Simple message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.READ,
            mutedStatus = true
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 2 ",
            messageAuthor = "Author",
            message = "Simple message",
            verified = true,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.DELIVERED,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 3",
            messageAuthor = "Author",
            message = "Simple message",
            verified = true,
            sendDate = "12:13",
            newMessageCount = 2,
            messageStatus = Status.READ,
            mutedStatus = true
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 4",
            messageAuthor = "Author",
            message = "Simple message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.UNREAD,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 5",
            messageAuthor = "Author",
            message = "Simple message",
            verified = true,
            sendDate = "12:13",
            newMessageCount = 11,
            messageStatus = Status.DELIVERED,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 6",
            messageAuthor = "Author",
            message = "Simple message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.READ,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 7",
            messageAuthor = "Author",
            message = "Simple message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 5,
            messageStatus = Status.DELIVERED,
            mutedStatus = true
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 8",
            messageAuthor = "Author",
            message = "Simple message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.READ,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 9",
            messageAuthor = "Author",
            message = "Simple message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.UNREAD,
            mutedStatus = false
        ),
        Chat(
            avatarImage = R.drawable.default_user_avatar,
            dialogName = "New chat 10",
            messageAuthor = "Author",
            message = "Simple message",
            verified = false,
            sendDate = "12:13",
            newMessageCount = 0,
            messageStatus = Status.UNREAD,
            mutedStatus = true
        )
    )
}