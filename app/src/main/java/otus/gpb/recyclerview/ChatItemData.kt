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

data class ChatItemData(val id: Int) {

    constructor(id: Int, empty: Boolean = false) : this(id)
    {
        if (!empty) {
            avatar = Random.nextInt(0, 8).toUByte()
            author = if (Random.nextInt(0, 2) == 1) "Автор $id" else ""
            title = "Заголовок $id"
            message = "Текст сообщения $id $avatar"

            status = ChatItemStatus.SCAM.doSet(Random.nextBits(9), (id % 5 == 0))
            status = ChatItemStatus.FAKE.doSet(status, (id % 6 == 0))
            if (ChatItemStatus.SCAM.isSet(status) || ChatItemStatus.FAKE.isSet(status)) status = ChatItemStatus.VERIFY.doSet(status, false)
        }
    }

    var avatar: UByte = 0u

    var status = 0

    var author = ""
    var title = ""
    var message = ""

    //val time: String = SimpleDateFormat("dd.MM.yyyy HH:mm").format(Timestamp(System.currentTimeMillis()))
    val time = System.currentTimeMillis()
}
