package otus.gpb.recyclerview

class Presenter {

    fun getChats() : List<ChatModel>{
        val mutableList = mutableListOf<ChatModel>()
        repeat(50){ counter ->
            mutableList += ChatModel(
                id = counter,
                messCount = counter,
                message = "Counter $counter",
                group = "System",
                check = true,
                scam = false,
                verified = true,
                mute = true,
                nickName = "User#$counter"
            )
        }
        return mutableList
    }

}