package otus.gpb.recyclerview

import androidx.annotation.DrawableRes

data class Chat(
    val id: Long, //ID
    val title: String, //Название чата
    val lastMessage: String, //Последнее сообщение
    val lastWriter: String?, //Последний писатель сообщения
    val avatar: String, //Аватар
    val checkBox: Boolean, //Выделен чат или нет.
    val lock: Boolean, //Приватный чат.
    val scam: Boolean, //SCAM
    val mute: Boolean, //Отключен звук
    val verified: Boolean, //Проверенный аккаунт
    val dateMessage: String, //Время сообщения
    val messageCheck: Boolean, //Прочитано сообщение
    val online: Boolean? = null//Онлайн. Только для пользователей.
)