package otus.gpb.recyclerview

class Dialogs {

    companion object {
        fun generateList() : List<ChatItem> {
            val list = mutableListOf<ChatItem>()
            var person = ChatItem(
                name = "Pizza",
                isMuted = true,
                title = "jija",
                message = "Yes, they are necessary",
                time = "11:38 AM",
                image = R.drawable.avatar1
            )
            list.add(person)

            return list
        }
    }
}