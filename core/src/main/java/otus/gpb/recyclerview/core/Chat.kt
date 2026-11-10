package otus.gpb.recyclerview.core

import androidx.annotation.ColorInt

/**
 * Всё, что нужно для отрисовки одной ячейки списка чатов
 */
data class Chat(
    val id: Long,
    val title: String,
    /** Автор последнего сообщения: есть только у групп, у личных чатов — null */
    val author: String?,
    val message: String,
    /** Цвет миниатюры вложения перед текстом; null — вложения нет */
    @ColorInt val previewColor: Int?,
    val time: String,
    val status: MessageStatus,
    val unreadCount: Int,
    val isMuted: Boolean,
    val isVerified: Boolean,
    val isScam: Boolean,
    val isPinned: Boolean,
    val hasMention: Boolean,
)

/**
 * Статус последнего исходящего сообщения
 */
enum class MessageStatus {
    NONE,
    SENT,
    READ,
}
