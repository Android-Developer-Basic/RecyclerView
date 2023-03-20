package otus.gpb.recyclerview

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.random.Random

object ChatCreator {

    private var index: Int = 0

    fun create(size : Int = 10): MutableList<ChatData> {
        val list = mutableListOf<ChatData>()
        for (i in 0..size) {
            list.add(generateChat())
        }
        return list
    }

    private fun generateChat(): ChatData {
        return ChatData(
            index++,
            getRandomString(10),
            getRandomString(5),
            getRandomString(15),
            R.drawable.round_circle_24,
            Random.nextInt(0, 2) == 1,
            Random.nextInt(0, 2) == 1,
            Random.nextInt(0, 2) == 1,
            getTime(),
            Random.nextInt(0, 2) == 1,
            Random.nextInt(0, 2) == 1,
            Random.nextInt(0, 100)
        )
    }

    private fun getRandomString(maxLength: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..Random.nextInt(3, maxLength + 1))
            .map { charset.random() }
            .joinToString("")
    }

    private fun getTime() : String {
        return DateTimeFormatter
            .ofPattern("HH:mm a")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
    }
}