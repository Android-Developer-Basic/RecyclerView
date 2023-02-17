package otus.gpb.recyclerview

class ChatDataGenerator {
    fun getChatData() : MutableList<ChatItem> {
        val data = ItemCreator()

        val generate = (1..10).map {
            with(data) {
                ChatItem(
                    profileImage = getProfileImage(),
                    name = getFullName(),
                    status = getStatus(),
                    haveStatus = getBoolean(),
                    chatMuted = getBoolean(),
                    scam = getBoolean(),
                    verified = getBoolean(),
                    message = getMessage(),
                    date = getDate(),
                    messageCounter = getCounterMessages(),
                    sendMessage = getBoolean(),
                    readMessage = getBoolean()
                )
            }
        }
        return generate.toMutableList()
    }
}