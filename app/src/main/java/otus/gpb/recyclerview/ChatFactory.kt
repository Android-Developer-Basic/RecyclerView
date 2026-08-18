package otus.gpb.recyclerview

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import java.util.Locale

object ChatFactory {
    private data class Sample(
        val title: String,
        val message: String,
        val initials: String,
        @ColorInt val color: Int,
        @DrawableRes val avatarResId: Int? = null,
        val verified: Boolean = false,
        val muted: Boolean = false,
        val pinned: Boolean = false,
        val online: Boolean = false,
        val mention: Boolean = false,
        val unread: Int = 0,
        val delivery: Chat.DeliveryState = Chat.DeliveryState.NONE,
        val state: Chat.MessageState = Chat.MessageState.REGULAR,
    )

    private val samples = listOf(
        Sample("Dima Murantsev", "Всегда пожалуйста :)", "DM", 0xFFD89B68.toInt(), R.drawable.avatar_dima, verified = true, pinned = true, delivery = Chat.DeliveryState.READ),
        Sample("Оффтопка", "А в вашем инфоне это есть?", "ОФ", 0xFF78A9C4.toInt(), R.drawable.avatar_offtopic, muted = true, pinned = true, online = true),
        Sample("Catbird", "печатает", "CB", 0xFF7C6AB0.toInt(), R.drawable.avatar_catbird, pinned = true, state = Chat.MessageState.TYPING),
        Sample("just design", "Вы: я хочу пить", "JD", 0xFF9193E8.toInt(), R.drawable.avatar_just_design, muted = true, mention = true, delivery = Chat.DeliveryState.READ),
        Sample("R4INB0W", "du biest mein sonnchein", "R4", 0xFF454D68.toInt(), R.drawable.avatar_r4inbow),
        Sample("Да. Нет.", "Красиво", "ДН", 0xFF3994C6.toInt(), R.drawable.avatar_yes_no, verified = true, unread = 50, delivery = Chat.DeliveryState.SENT),
        Sample("SMDDN", "Сними с меня скам!", "SM", 0xFF5B7EA6.toInt(), R.drawable.avatar_smddn),
        Sample("Android Developers", "RecyclerView умеет больше, чем кажется", "AD", 0xFF3DDC84.toInt(), verified = true, unread = 3),
        Sample("Учебная группа", "Задание сдано на проверку", "УГ", 0xFFF2A65A.toInt(), muted = true, unread = 12),
        Sample("Saved Messages", "Макет Telegram", "SM", 0xFF4AA3DF.toInt(), delivery = Chat.DeliveryState.READ),
    )

    fun createPage(page: Int, pageSize: Int = 10): List<Chat> = List(pageSize) { index ->
        val absoluteIndex = page * pageSize + index
        val sample = samples[absoluteIndex % samples.size]
        val cycle = absoluteIndex / samples.size
        sample.toChat(
            id = absoluteIndex.toLong(),
            suffix = if (cycle == 0) "" else " ${cycle + 1}",
            minutesAgo = absoluteIndex % 60,
        )
    }

    private fun Sample.toChat(id: Long, suffix: String, minutesAgo: Int): Chat {
        val totalMinutes = 11 * 60 + 38 + minutesAgo
        return Chat(
            id = id,
            title = title + suffix,
            message = message,
            time = String.format(Locale.US, "%02d:%02d", totalMinutes / 60, totalMinutes % 60),
            avatarLetters = initials,
            avatarColor = color,
            avatarResId = avatarResId,
            unreadCount = unread,
            isVerified = verified,
            isMuted = muted,
            isPinned = pinned,
            isOnline = online,
            hasMention = mention,
            deliveryState = delivery,
            messageState = state,
        )
    }
}