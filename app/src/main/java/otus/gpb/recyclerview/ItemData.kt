package otus.gpb.recyclerview

data class ItemData @JvmOverloads constructor(
    val name: String,
    val avatar: Int,
    val caption: String? = null,
    val message: String,
    val time: String,
    val count: Int? = null,
    val isMute: Boolean = false,
    val isVerified: Boolean = false,
    val isScam: Boolean = false,
    val isChecked: Boolean = false,
    val isRead: Boolean = false
)
