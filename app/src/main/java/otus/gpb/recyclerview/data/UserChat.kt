package otus.gpb.recyclerview.data

data class UserChat(
    val id: Int,
    val username: String,
    val lastMessage: String,
    val avatarUrl: String,
    var isVerified: Boolean,
    var isMuted: Boolean,
    var isRead: Boolean,
    var isScam: Boolean,
    var isOnline: Boolean,
    var counterMessages: Int,
    val messageTime: String
)

val userChats = listOf(
    UserChat(
        id = 1,
        username = "Павел Дуров",
        lastMessage = "Пойдём сегодня на футбол?",
        avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/3/33/Espaguetis_carbonara.jpg",
        isVerified = true,
        isMuted = false,
        isRead = false,
        isScam = false,
        isOnline = true,
        counterMessages = 1,
        messageTime = "06.07.2025 13:05"
    ),
    UserChat(
        id = 2,
        username = "Бадави",
        lastMessage = "Сроооочно зайди да",
        avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/3/33/Espaguetis_carbonara.jpg",
        isVerified = false,
        isMuted = true,
        isRead = false,
        isScam = false,
        isOnline = false,
        counterMessages = 10,
        messageTime = "02.07.2025 13:46"
    ),
    UserChat(
        id = 3,
        username = "Олег Тинькофф",
        lastMessage = "Скинь 3 цифры на обороте карты",
        avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/3/33/Espaguetis_carbonara.jpg",
        isVerified = false,
        isMuted = false,
        isRead = false,
        isScam = true,
        isOnline = true,
        counterMessages = 5,
        messageTime = "25.01.2024 16:18"
    ),
)
