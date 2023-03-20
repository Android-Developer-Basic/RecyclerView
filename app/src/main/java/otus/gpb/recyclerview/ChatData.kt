package otus.gpb.recyclerview

data class ChatData(
    val id: Int,
    val title: String,
    val author: String,
    val message: String,
    val avatar: Int,
    val isMute: Boolean,
    val isVerify: Boolean,
    val isScam: Boolean,
    val dataTime: String,
    val isChecked: Boolean,
    val isRead: Boolean,
    val counter: Int
)
