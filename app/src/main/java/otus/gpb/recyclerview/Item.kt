package otus.gpb.recyclerview

data class Item(
    val avatar: Int,
    val name: String,
    val message: String,
    val comment: String = "",
    val date: String,
    val mute: Boolean = false,
    val verify: Boolean = false,
    val unreadMessages: Int,
    val check: Boolean = false,
    val doubleCheck: Boolean = false,
    val scum: Boolean = false,
    val pictureInMessage: Boolean = false
)