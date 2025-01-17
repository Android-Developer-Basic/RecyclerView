package otus.gpb.recyclerview

object ChatItemDataGenerator {

    fun generateChatItemsList(count: Int): MutableList<ChatItem> {
        return MutableList(count) {
            ChatItem(
                id = it,
                username = listOf(
                    "Dima Murantsev",
                    "Catbird",
                    "just design",
                    "R4IN80W",
                    "SMDDN",
                    "Midnight",
                    "Nikita Dmitriev",
                    "Evgenia Smud",
                    "MidNighT",
                    "bravo-social"
                ).random(),
                message = listOf(
                    "Hello",
                    "Good day",
                    "du biest mein sonnechein",
                    "How're you?",
                    "let's make it",
                    "in the evening, i suppose",
                    "a lot of fun",
                    "Have you seen this?",
                    "yep",
                    "don't think so",
                    "you won't believe"
                ).random(),
                userImage = listOf(
                    R.drawable.ic_avatar_1,
                    R.drawable.ic_avatar_2,
                    R.drawable.ic_avatar_3,
                    R.drawable.ic_avatar_4,
                    R.drawable.ic_avatar_5,
                    R.drawable.ic_avatar_6,
                    R.drawable.ic_avatar_7
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
