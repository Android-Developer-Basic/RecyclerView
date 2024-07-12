package otus.gpb.recyclerview

class Dialogs {

    companion object {
        fun generateList() : List<ChatItem> {
            val list = mutableListOf<ChatItem>()
            var person = ChatItem(
                name = "Pizza",
                isMuted = true,
                isVerified = true,
                isScam = false,
                hasPrevPic = true,
                title = "jija",
                message = "Yes, they are necessary",
                time = "11:38 AM",
                image = R.drawable.avatar1
            )
            list.add(person)

            repeat(49){
            val person = ChatItem(
                name = "Name $it",
                isMuted = false,
                isVerified = true,
                isScam = true,
                hasPrevPic = false,
                title = "Name title $it",
                message = "This is message $it",
                time = "12:12 AM",
                image = R.drawable.default_avatar
            )
            list.add(person)
        }
            return list.toList()
        }
    }
}