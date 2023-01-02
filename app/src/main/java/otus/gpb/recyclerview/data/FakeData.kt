package otus.gpb.recyclerview.data

import otus.gpb.recyclerview.R

class FakeData {
    // имена взяты с сайта https://www.fakenamegenerator.com/
    private val nameList = listOf(
        "Eddie",
        "Vicente",
        "Allan",
        "Howard",
        "Geoffrey",
        "Jena",
        "Beatris",
        "Derek",
        "Chris",
        "Ebony",
        "Delbert",
        "Scott",
        "Betty"
    )

    private val lastNameList = listOf(
        "Castillon",
        "Crider",
        "Miller",
        "Lindsey",
        "Silverberg",
        "Tucker",
        "Stapleton",
        "Cruz",
        "Quintero",
        "Murphy",
        "Provencher",
        "Gardner",
        "Haffey"
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

    fun getFakeName() = "${nameList.random()} ${lastNameList.random()}"

    fun getFakeStatus() = statusList.random()

    fun getFakeLastMessage() = lastMessagePreview.random()

    fun getFakeBoolean() = listOf(true, false).random()

    fun getFakeProfileImage() = imageList.random()

    fun getFakeCounter() = listOf(0, 11, 0, 7, 3, 15, 0, 9, 5).random()

    fun getFakeDate(): String {
        val hour = (10..23).random()
        val minutes = (10..59).random()
        val amPm = listOf("AM", "PM").random()

        return "$hour:$minutes $amPm"
    }
}
