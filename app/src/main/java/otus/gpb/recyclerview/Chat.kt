package otus.gpb.recyclerview


class Chat(

    val userAvatar:Int? = null,
    val userName: String,
    val message: String,
    val time:String,
    val flags:Set<ChatFlags>,
    val numberOfMessages:Int? = null
)
