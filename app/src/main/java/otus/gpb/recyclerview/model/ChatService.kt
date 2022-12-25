package otus.gpb.recyclerview.model

object ChatService {

    private var chatList = mutableListOf<Chat>()

    init {
        val fake = FakeData()

        val generate = (1..20).map {
            with(fake) {
                Chat(
                    profileImage = getFakeProfileImage(),
                    name = getFakeName(),
                    status = getFakeStatus(),
                    haveStatus = getFakeBoolean(),
                    isMuted = getFakeBoolean(),
                    isVerified = getFakeBoolean(),
                    lastMessage = getFakeLastMessage(),
                    date = getFakeDate(),
                    messageCount = getFakeCounter(),
                    isDone = getFakeBoolean(),
                    isDoneAll = getFakeBoolean()
                )
            }
        }

        chatList = generate.toMutableList()
    }

    fun getChatList() = chatList
}
