package otus.gpb.recyclerview.model

data class Chat(
    val id: ULong,
    val nameUserInChat: String,
    val statusNotification: Boolean, //без уведомлений
    val previewText: String,
    val timeLastMessage: String,
    val statusMessage: Boolean, // прочитано/непрочитано
    val countNewMessage: UInt, // количество новых сообщений
    val accountStatus: AccountStatus
)


enum class AccountStatus{
    OFFICIAL, SCAM
}
