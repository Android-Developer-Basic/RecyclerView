package otus.gpb.recyclerview.core

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale
import kotlin.random.Random

/**
 * Генерирует чаты постранично — имитация загрузки из сети
 */
class ChatRepository(
    /** Момент, от которого отсчитывается время сообщений; сохраняется, чтобы восстановленные чаты совпали */
    val baseTime: LocalDateTime = LocalDateTime.now(),
    private val pageSize: Int = 20,
) {

    var loadedCount = 0L
        private set

    fun loadPage(): List<Chat> = List(pageSize) { createChat(loadedCount++) }

    /**
     * Заново создаёт первые [count] чатов — те же самые, что были выданы до пересоздания
     */
    fun restore(count: Long): List<Chat> {
        loadedCount = count
        return (0 until count).map(::createChat)
    }

    private fun createChat(id: Long): Chat {
        // Генератор с seed = id: один и тот же id всегда даёт один и тот же чат
        val random = Random(id)
        val isGroup = random.nextBoolean()
        val isMuted = random.nextInt(4) == 0
        val isOutgoing = !isGroup && random.nextBoolean()
        val unreadCount = if (!isOutgoing && random.nextInt(3) == 0) random.nextInt(1, 120) else 0

        return Chat(
            id = id,
            title = (if (isGroup) GROUPS else PEOPLE).random(random),
            author = if (isGroup) AUTHORS.random(random) else null,
            message = MESSAGES.random(random),
            previewColor = if (random.nextInt(4) == 0) PREVIEW_COLORS.random(random) else null,
            time = formatTime(baseTime.minusMinutes(id * 97)),
            status = when {
                !isOutgoing -> MessageStatus.NONE
                random.nextBoolean() -> MessageStatus.READ
                else -> MessageStatus.SENT
            },
            unreadCount = unreadCount,
            isMuted = isMuted,
            isVerified = random.nextInt(5) == 0,
            isScam = random.nextInt(12) == 0,
            isPinned = id < 2,
            hasMention = isGroup && unreadCount > 0 && random.nextBoolean(),
        )
    }

    private fun formatTime(time: LocalDateTime): String {
        val days = ChronoUnit.DAYS.between(time.toLocalDate(), baseTime.toLocalDate())
        val pattern = when {
            days == 0L -> "HH:mm"
            days < 7 -> "EEE"
            else -> "MMM dd"
        }
        return time.format(DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH))
    }

    private companion object {
        val PEOPLE = listOf(
            "Dima Murantsev", "Catbird", "Elon", "Pasha", "Karina", "Marilyn",
            "ShaMsiDDin", "R4IN80W", "Anna Petrova", "John Smith", "Oleg", "Maria",
        )
        val GROUPS = listOf(
            "SnejUgal News", "just design", "Yes. No", "Pizza", "Telegram Support",
            "Android Devs", "Kotlin Community", "OTUS", "Weekend Plans",
        )
        val AUTHORS = listOf("Nikolay", "You", "Anno", "jija", "Support", "Alex", "Kate")
        val MESSAGES = listOf(
            "you are welcome :)", "I want pizza", "du biest mein sonnechein", "I see all :))",
            "That`s better", "How are u?", "I love /r/Reddit!", "Yes it happened.", "Okay",
            "Will it ever happen", "Yes, they are necessary", "Don`t swipe me!",
            "See you tomorrow at the office", "Photo",
        )
        val PREVIEW_COLORS = listOf(
            0xFF5B8DEF.toInt(), 0xFF7BC862.toInt(), 0xFFFAA774.toInt(), 0xFF8E8E93.toInt(),
        )
    }
}
