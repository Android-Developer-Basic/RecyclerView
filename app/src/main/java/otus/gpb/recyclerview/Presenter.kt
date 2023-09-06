package otus.gpb.recyclerview

class Presenter {

    private val avatars = listOf(
        R.drawable.avatar_tg,
        R.drawable.avatar,
        R.drawable.avatar_0,
        R.drawable.avatar_1,
        R.drawable.avatar_2,
        R.drawable.avatar_3
    )

    private val nickNames = listOf(
        "Pizza",
        "Elon",
        "Pashka",
        "Telega",
        "Karina",
        "Marlin",
        "Oleg",
        "Sasha",
        "OTUS",
        "Alina",
        "Anton"
    )

    private val groups = listOf(
        "Support",
        "Friend",
        "Work"
    )

    fun getChats(startPosition: Int = 0) : List<ChatModel>{
        val mutableList = mutableListOf<ChatModel>()
        repeat(10){ counter ->
            mutableList += ChatModel(
                id = startPosition + counter,
                avatarImage = avatars.random(),
                messCount = startPosition + counter,
                message = "Counter ${startPosition + counter}",
                group = if(counter % 2 == 0) "" else groups.random() ,
                check = counter % 2 == 0,
                scam = counter % 2 == 1,
                verified = counter % 3 == 0,
                mute = counter % 3 == 1,
                nickName = nickNames.random() +  " ${startPosition + counter}"
            )
        }
        return mutableList
    }

}