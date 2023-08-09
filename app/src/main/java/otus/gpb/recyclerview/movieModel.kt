package otus.gpb.recyclerview

data class movieModel(
    val id: Int,
    val avatar: Int,
    val name: String,
    val message: String,
    val time: String,
    val cont: Int = 0,
    val author: String = "",
    val isMute: Boolean = false,
    val isVerified: Boolean = false,
    val isScam: Boolean = false,
    val isCheck: Boolean = false,
    val isRead: Boolean = false,
    val isPreview: Boolean = false
)

