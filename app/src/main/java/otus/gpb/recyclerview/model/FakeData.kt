package otus.gpb.recyclerview.model

import otus.gpb.recyclerview.R

class FakeData {
    // имена взяты с сайта https://www.fakenamegenerator.com/
    private val nameList = listOf(
        "Eddie Castillon",
        "Vicente Crider",
        "Allan Miller",
        "Howard Lindsey",
        "Geoffrey Silverberg",
        "Jena Tucker",
        "Beatris Stapleton",
        "Derek Cruz",
        "Chris Quintero",
        "Ebony Murphy",
        "Delbert Provencher",
        "Scott Gardner",
        "Betty Haffey"
    )

    // статусы взяты с сайта https://randomwordgenerator.com/
    private val statusList = listOf(
        "drawing",
        "athlete",
        "lost",
        "knowledge",
    )

    // сообщения взяты с сайта https://www.coolgenerator.com/sentence-generator
    private val lastMessagePreview = listOf(
        "Are you staying with him?",
        "Iron is a useful metal.",
        "Tom could've figured that out.",
        "I've asked Tom to help.",
        "I just started to learn Esperanto.",
        "Do you run every day?",
        "I shouldn't have come here.",
        "He should have done it Aug.",
        "We can still get there on time."
    )

    private val imageList = listOf(
        R.drawable.account_circle_blue,
        R.drawable.account_circle_indigo,
        R.drawable.account_circle_green,
        R.drawable.account_circle_red,
        R.drawable.account_circle_yellow
    )

    fun getFakeName() = nameList.shuffled().first()

    fun getFakeStatus() = statusList.shuffled().first()

    fun getFakeLastMessage() = lastMessagePreview.shuffled().first()

    fun getFakeBoolean() = listOf(true, false).shuffled().first()

    fun getFakeProfileImage() = imageList.shuffled().first()

    fun getFakeCounter() = listOf(0, 11, 0, 7, 3, 15, 0, 9, 5).shuffled().first()

    fun getFakeDate(): String {
        val hour = (10..23).shuffled().first()
        val minutes = (10..59).shuffled().first()
        val amPm = listOf("AM", "PM").shuffled().first()

        return "$hour:$minutes $amPm"
    }
}
