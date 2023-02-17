package otus.gpb.recyclerview

    data class ChatItem(
        val profileImage: Int,
        val name: String,
        val status: String,
        val haveStatus: Boolean,
        val chatMuted: Boolean,
        val scam: Boolean,
        val verified: Boolean,
        val message: String,
        val date: String,
        val messageCounter: Int,
        val sendMessage: Boolean,
        val readMessage: Boolean
    )
