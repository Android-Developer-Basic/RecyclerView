package otus.gpb.recyclerview

object ChatGenerator {
    private var id = 0

    fun generateItems() = listOf(
        Chat(
            id++,
            R.drawable.pizza,
            "Pizza",
            "jija",
            "Yes, they are necessary",
            R.drawable.preview,
            false,
            false,
            true,
            "11:38 AM",
            Chat.Status.NONE,
            0
        ),
        Chat(
            id++,
            R.drawable.elon,
            "Elon",
            null,
            "I love /r/Reddit!",
            null,
            false,
            false,
            true,
            "12:44 AM",
            Chat.Status.NONE,
            0
        ),
        Chat(
            id++,
            R.drawable.pasha,
            "Pasha",
            null,
            "How are u?",
            null,
            false,
            true,
            true,
            "Fri",
            Chat.Status.NONE,
            0
        ),
        Chat(
            id++,
            R.drawable.telegram,
            "Telegram Support",
            "Support",
            "Yes it happened.",
            null,
            false,
            true,
            false,
            "Thu",
            Chat.Status.NONE,
            1
        ),
        Chat(
            id++,
            R.drawable.karina,
            "Karina",
            null,
            "Okay",
            null,
            false,
            false,
            false,
            "Wed",
            Chat.Status.CHECKED,
            0
        ),
        Chat(
            id++,
            R.drawable.marilyn,
            "Marilyn",
            null,
            "Will it ever happen",
            null,
            true,
            false,
            false,
            "May 02",
            Chat.Status.READ,
            0
        ),
        Chat(
            id++,
            R.drawable.pizza,
            "Pizza",
            "jija",
            "Yes, they are necessary",
            R.drawable.preview,
            false,
            false,
            true,
            "11:38 AM",
            Chat.Status.NONE,
            0
        ),
        Chat(
            id++,
            R.drawable.elon,
            "Elon",
            null,
            "I love /r/Reddit!",
            null,
            false,
            false,
            true,
            "12:44 AM",
            Chat.Status.NONE,
            0
        ),
        Chat(
            id++,
            R.drawable.pasha,
            "Pasha",
            null,
            "How are u?",
            null,
            false,
            true,
            true,
            "Fri",
            Chat.Status.NONE,
            0
        ),
        Chat(
            id++,
            R.drawable.telegram,
            "Telegram Support",
            "Support",
            "Yes it happened.",
            null,
            false,
            true,
            false,
            "Thu",
            Chat.Status.NONE,
            1
        )
    )
}