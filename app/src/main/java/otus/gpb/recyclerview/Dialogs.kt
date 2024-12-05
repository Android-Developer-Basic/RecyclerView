package otus.gpb.recyclerview

class Dialogs {

    companion object {
        fun generateList() : List<ChatItem> {
            val list = mutableListOf<ChatItem>()
            var person = ChatItem(
                id = 1,
                name = "Pizza",
                isMuted = true,
                isVerified = false,
                isScam = false,
                hasPrevPic = true,
                title = "jija",
                message = "Yes, they are necessary",
                messageState = MessageState.IS_INCOMING,
                time = "11:38 AM",
                messageCounter = null,
                image = R.drawable.avatar1
            )
            list.add(person)

            person = ChatItem(
                id = 2,
                name = "Elon",
                isMuted = true,
                isVerified = false,
                isScam = false,
                hasPrevPic = false,
                title = "",
                message = "I love /r/Reddit!",
                messageState = MessageState.IS_INCOMING,
                time = "12:44 AM",
                messageCounter = null,
                image = R.drawable.avatar2
            )
            list.add(person)

            person = ChatItem(
                id = 3,
                name = "Pasha",
                isMuted = true,
                isVerified = true,
                isScam = false,
                hasPrevPic = false,
                title = null,
                message = "How are u?",
                messageState = MessageState.IS_INCOMING,
                time = "Fri",
                messageCounter = null,
                image = R.drawable.avatar3
            )
            list.add(person)

            person = ChatItem(
                id = 4,
                name = "Tim Cook",
                isMuted = true,
                isVerified = true,
                isScam = false,
                hasPrevPic = false,
                title = "Boss of Apple",
                message = "Android is better",
                messageState = MessageState.IS_READ,
                time = "15:02 AM",
                messageCounter = null,
                image = R.drawable.avatar4
            )
            list.add(person)

            person = ChatItem(
                id = 5,
                name = "Telegram Support",
                isMuted = false,
                isVerified = true,
                isScam = false,
                hasPrevPic = false,
                title = "Support",
                message = "Yes it happened.",
                messageState = MessageState.IS_INCOMING,
                time = "Thu",
                messageCounter = 1,
                image = R.drawable.avatar5
            )
            list.add(person)

            person = ChatItem(
                id = 6,
                name = "Karina",
                isMuted = false,
                isVerified = false,
                isScam = false,
                hasPrevPic = false,
                title = "",
                message = "Okay",
                messageState = MessageState.IS_SENT,
                time = "Wed",
                messageCounter = null,
                image = R.drawable.avatar6
            )
            list.add(person)

            person = ChatItem(
                id = 7,
                name = "Marilyn",
                isMuted = false,
                isVerified = false,
                isScam = true,
                hasPrevPic = false,
                title = "",
                message = "Will it ever happen",
                messageState = MessageState.IS_READ,
                time = "May 02",
                messageCounter = null,
                image = R.drawable.avatar7
            )
            list.add(person)

            repeat(43){
            val person = ChatItem(
                id = it + 8,
                name = "Name ${it + 8}",
                isMuted = false,
                isVerified = true,
                isScam = true,
                hasPrevPic = false,
                title = "Name title $it",
                message = "This is message $it",
                messageState = MessageState.IS_SENT,
                time = "12:12 AM",
                messageCounter = null,
                image = R.drawable.default_avatar
            )
            list.add(person)
        }
            return list.toList()
        }
    }
}