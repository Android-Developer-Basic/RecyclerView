package otus.gpb.recyclerview

class ChatRepository {

    companion object {
        var listChat = mutableListOf(
            Chat(1, "Chat1", "Message1", "Writer1", "", false, false, false, false, true,"11:01", true),
            Chat(2, "Chat2", "Message2", "Writer2", "", true, true, false, true, false,"11:02", false),
            Chat(3, "Chat3", "Message3", "Writer3", "", false, false, false, false, false,"11:03", true),
            Chat(4, "Chat4", "Message4", "Writer4", "", true, false, true, false, true,"11:04", true),
            Chat(5, "Chat5", "Message5", "Writer5", "", true, true, false, false, true,"11:05", true),
            Chat(6, "Chat6", "Message6", "Writer6", "", false, false, false, true, true,"11:06", false),
            Chat(7, "Chat7", "Message7", "Writer7", "", true, false, false, false, false,"11:07", true),
            Chat(8, "Chat8", "Message8", "Writer8", "", false, true, false, true, false,"11:08", true),
            Chat(9, "Chat9", "Message9", "Writer9", "", true, false, true, false, true,"11:09", true),
            Chat(10, "Chat10", "Message10", "Writer10", "", false, false, false, true, true,"11:10", true),
            Chat(11, "Chat11", "Message11", "Writer11", "", false, false, false, false, true,"11:11", true),
            Chat(12, "Chat12", "Message12", "Writer12", "", true, true, false, true, false,"11:12", true),
            Chat(13, "Chat13", "Message13", "Writer13", "", false, false, false, false, false,"11:13", true),
            Chat(14, "Chat14", "Message14", "Writer14", "", true, false, true, false, true,"11:14", false),
            Chat(15, "Chat15", "Message15", "Writer15", "", true, true, false, false, true,"11:15", true),
            Chat(16, "Chat16", "Message16", "Writer16", "", false, false, false, true, true,"11:16", false),
            Chat(17, "Chat17", "Message17", "Writer17", "", true, false, false, false, false,"11:17", true),
            Chat(18, "Chat18", "Message18", "Writer18", "", false, true, false, true, false,"11:18", true),
            Chat(19, "Chat19", "Message19", "Writer19", "", true, false, true, false, true,"11:19", true),
            Chat(20, "Chat20", "Message20", "Writer20", "", false, false, false, true, true,"11:20", true)
        )
    }
}