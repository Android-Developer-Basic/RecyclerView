package otus.gpb.recyclerview.chat

data class ChatData(
    var id: Int,
    var avatar: Int,
    var avatarActionIcon: Int,

    var lockIcon: Int,
    val title: String,
    var verifiedIcon: Int,

    var messageAuthor: String,

    var previewIcon: Int,
    val message: String,

    var checkIcon: Int,
    var timeService: String,

    var mentionBlock: Boolean,
    var pinnedIcon: Int,
)