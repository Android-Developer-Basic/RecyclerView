package otus.gpb.recyclerview

import com.github.javafaker.Faker

data class ChatTlgItem(
// TODO There should be a real structure with Tg chat elements
    val chatName: String,      // Chat name
    val messageAuthor: String, // Message author
    val messageTitle: String,  // Message fragment
    val messageTime: String,   // Message recv time
    val messageCnt: Int,       // Message count
    val photo: String          // Avatar
)

class ChatTlgService( private val isNewList: Boolean ){

    private var itemsCnt:Int = 6
    private var chatList = mutableListOf<ChatTlgItem>()

    init {
        if( isNewList ) itemsCnt = 20
        val faker = Faker.instance()

        chatList = (0..itemsCnt).map {
            ChatTlgItem(
                chatName = faker.name().fullName(),
                messageAuthor = faker.name().fullName(),
                messageTitle = faker.book().title(),
                messageTime = faker.numerify("##:## AM"),//"11:25 AM",
                messageCnt = faker.number().numberBetween(1,100),
                photo = IMAGES[it % IMAGES.size],
            )
        }.toMutableList()
    }

    fun getTlgChat() : List<ChatTlgItem>{
        return chatList
    }

    companion object {
        private val IMAGES = mutableListOf(
            "avatar_1",
            "avatar_2",
            "avatar_3",
            "avatar_4",
            "avatar_5",
            "avatar_6"
        )
    }
}