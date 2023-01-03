package otus.gpb.recyclerview.service

import otus.gpb.recyclerview.data.FakeData
import otus.gpb.recyclerview.model.Chat

class ChatService {

    fun getChatList() : MutableList<Chat> {
        val fake = FakeData()

        val generate = (1..10).map {
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

        return generate.toMutableList()
    }
}
