package otus.gpb.recyclerview

import kotlin.random.Random


enum class ChatItemStatus(private val value: Int) {
    VERIFY(1),
    MUTE(1 shl 1),
    SECRET(1 shl 2),
    CHECK(1 shl 3),
    READ(1 shl 4),
    SCAM(1 shl 5),
    FAKE(1 shl 6),
    ATTACH(1 shl 7),
    EMAIL(1 shl 8);

    fun isSet(statusValue: Int) = (statusValue and value) != 0
    fun doSet(statusValue: Int, set: Boolean = false) = if (set) statusValue or value else statusValue and (value xor -1)
}

data class ChatItemData(val id: Int, val author: String, val title: String = "", val message: String = "", val avatar: UByte = 0u, val status: Int = 0) {
    val time = System.currentTimeMillis()
}

fun createChatItemData(id: Int, empty: Boolean = false): ChatItemData {

    return if (empty) ChatItemData(id, "anonymous")
        else {

        val avatar = Random.nextInt(0, 8)

        var status = Random.nextBits(9)
        status = ChatItemStatus.SCAM.doSet(status, (status % 5 == 0))
        status = ChatItemStatus.FAKE.doSet(status, (status % 6 == 0))
        if (ChatItemStatus.SCAM.isSet(status) || ChatItemStatus.FAKE.isSet(status)) status = ChatItemStatus.VERIFY.doSet(status, false)

        ChatItemData(
            id,
            if (Random.nextInt() % 2 == 0) "Автор $id" else "",
            "Заголовок $id",
            "Текст сообщения $id $avatar",
            avatar.toUByte(),
            status
        )
    }
}
