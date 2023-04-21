package otus.gpb.recyclerview

data class ItemData (
    val avatar: Int,
    val chatName: String,
    val message: String,
    val time: String,
    val author: String = "",
    val isMute: Boolean = false,
    val isVerify: Boolean = false,
    val isScam: Boolean = false,
    val isCheck: Boolean = false,
    val isDoubleCheck: Boolean = false,
    val unreadCount: Int,
    val unreadIcon: Boolean = false
)
