package otus.gpb.recyclerview

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

data class Chat(
    val id: Long,
    val title: String,
    val message: String,
    val time: String,
    val avatarLetters: String,
    @ColorInt val avatarColor: Int,
    @DrawableRes val avatarResId: Int? = null,
    val unreadCount: Int = 0,
    val isVerified: Boolean = false,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false,
    val isOnline: Boolean = false,
    val hasMention: Boolean = false,
    val deliveryState: DeliveryState = DeliveryState.NONE,
    val messageState: MessageState = MessageState.REGULAR,
) {
    enum class DeliveryState { NONE, SENT, READ }
    enum class MessageState { REGULAR, DRAFT, TYPING }
}