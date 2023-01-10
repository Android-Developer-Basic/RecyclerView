package otus.gpb.recyclerview.utils

import otus.gpb.recyclerview.model.TelegramChatMessage

class TestDataGenerator {

    fun generateData(count: Int): List<TelegramChatMessage> {
        val list = ArrayList<TelegramChatMessage>()
        for (i in 0..count) {
            list.add(
                TelegramChatMessage(
                    i * System.currentTimeMillis().toInt(),
                    "",
                    "Azaza",
                    "azaza",
                    123.toString()
                )
            )
        }
        return list
    }
}