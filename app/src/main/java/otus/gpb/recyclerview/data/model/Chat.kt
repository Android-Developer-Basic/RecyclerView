package otus.gpb.recyclerview.data.model

import androidx.annotation.DrawableRes


data class Chat(
    var id: Int, // позиция
    val nameUserInChat: String, // название чата
    val titleText: String,
    @DrawableRes val  urlAvatar: Int, //String,ссылка на аватар, либо с интернета либо с файлов
    val statusNotification: Boolean, //без уведомлений
    val previewText: String,
    val timeLastMessage: String,
    val statusMessage: Boolean, // прочитано/непрочитано
    val countNewMessage: Int, // количество новых сообщений
    val accountStatus: AccountStatus//иконка возле аватарки
)


enum class AccountStatus{
    OFFICIAL, SCAM, Normal, Group
}
