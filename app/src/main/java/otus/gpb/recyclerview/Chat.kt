package otus.gpb.recyclerview


data class Chat(


    val userAvatar:Int? = null,
    val userName: String,
    val message: String,
    val time:String,
    val markers:List<Boolean>
)
