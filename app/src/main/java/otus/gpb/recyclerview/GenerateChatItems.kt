package otus.gpb.recyclerview

import kotlin.random.Random

class GenerateChatItems {
    private val list = mutableListOf<ChatItem>()

    fun getList(): MutableList<ChatItem>{
        for (i in 1..20) {
            val mainName = getRandomMainName()
            val secondaryName = getRandomSecondaryName()
            val textMessage = getRandomMessage()
            val textTime = getTextTime()
            val isMuted = Random.nextBoolean()
            val isVerified = Random.nextBoolean()
            val messageImg = getRandomImgMessage()
            val stateMessage = getRandomState()
            val isFavour = Random.nextBoolean()
            val mainImg = getRandomAvatar()
            list.add(ChatItem(mainName, secondaryName, textMessage, textTime, isMuted, isVerified, messageImg, stateMessage, isFavour, mainImg))
        }
    return list
    }

    private fun getRandomMainName(): String {
        val list = listOf("Denis Milkov", "Elton", "Ivan Ivanov", "Elon Mask", "Oliver", "Grozny", "Kotlin", "Petr", "Hozy")
        return list.random()
    }
    private fun getRandomSecondaryName() : String? {
        val list = listOf("Boss", "Friend", "No name", "Doctor", "Developer", "Funny man", null, null, null)
        return list.random()
    }
    private fun getRandomMessage() : String {
        val list = listOf("Hello!", "How are you?", "Cant answer right now", "What are you doing?", "Im watching TV", "...")
        return list.random()
    }
    private fun getTextTime() : String {
        val list = listOf("12:46", "Fri", "Mon", "18:56", "00:00", "Wen", "15:33", "19:00", "09:00")
        return list.random()
    }
    private fun getRandomAvatar(): Int {
        val list = listOf(R.drawable.avatar4, R.drawable.avata2r, R.drawable.avata22r, R.drawable.ava)
        return list.random()
    }
    private fun getRandomState(): Int {
        val list = listOf(R.drawable.readed, R.drawable.not_readed, R.drawable.state_null)
        return list.random()
    }
    private fun getRandomImgMessage(): Int? {
        val list = listOf(R.drawable.avatar, null, null, null)
        return list.random()
    }
}