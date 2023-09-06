package otus.gpb.recyclerview

data class ChatModel(
    val id: Int = 0,
    val avatarImage: Int = R.drawable.avatar_tg,
    val previewImage: Int = R.drawable.mess_photo_preview,
    val nickName: String = "NickName",
    val group: String = "",
    val message: String = "",
    val lastTime: String = "Sun",
    val messCount: Int = 0,
    val check: Boolean = false,
    val scam: Boolean = false,
    val mute: Boolean = false,
    val verified: Boolean = false
)