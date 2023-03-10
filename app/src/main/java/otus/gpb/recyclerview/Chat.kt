package otus.gpb.recyclerview

import java.util.Date

class Chat(
    val id: Int,
    val name: String,
    val messageAuthor: String = "",
    val message: String,
    val numberMessage: Int,
    var isGroup: Boolean = false,
    var isSend: Boolean = false,
    var isRead: Boolean = false,
    var isMute: Boolean = false,
    var isScam: Boolean = false,
    var isVerified: Boolean = false,
    )