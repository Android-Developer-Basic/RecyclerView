package otus.gpb.recyclerview.model

data class ChatModel(
    val id: Int,
    val avatar: Int,
    val preview: Int?,
    val nick: String,
    val status: String,
    val last_massage: String,
    val time: String,
    val count: Int = 0,
    val check: Int?,
    val isSCAM: Boolean = false,
    val isMute: Boolean = false,
    val isVerified: Boolean = false
)