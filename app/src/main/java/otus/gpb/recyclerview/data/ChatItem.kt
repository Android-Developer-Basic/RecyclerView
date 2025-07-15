package otus.gpb.recyclerview.data

sealed class ChatItem() {
    data class Group(val chat: GroupChat) : ChatItem()
    data class User(val chat: UserChat) : ChatItem()
}