package otus.gpb.recyclerview

object ChatDataGenerator {

    private var lastId = 0
    fun generateChatItemsList(count: Int): MutableList<ChatItem> {
        return MutableList(count) {
            ChatItem(
                id = lastId++,
                username = listOf(
                    "Anton Karavaev",
                    "MyWife",
                    "George",
                    "AK-47",
                    "Chernomor",
                    "Cat",
                    "Donald Trump",
                    "Paul McCartney",
                    "John Lennon",
                    "Eddie Van Halen"
                ).random(),
                message = listOf(
                    "Hello",
                    "Good day",
                    "Здоровеньки булы...",
                    "How're you?",
                    "Шалом алейхем!",
                    "Хай, пипл!"
                ).random(),
                userImage = listOf(
                    R.drawable.avatar_1,
                    R.drawable.avatar_2,
                    R.drawable.avatar_3,
                    R.drawable.avatar_4,
                    R.drawable.avatar_5,
                    R.drawable.avatar_6,
                    R.drawable.avatar_7,
                    R.drawable.avatar_8,
                    R.drawable.avatar_9,
                    R.drawable.avatar_10
                ).random(),
                date = listOf(
                    "14:28",
                    "13:45",
                    "17:20",
                    "21:57",
                    "18:12"
                ).random()
            )
        }
    }
}