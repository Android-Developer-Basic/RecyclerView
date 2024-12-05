package otus.gpb.recyclerview

import androidx.annotation.DrawableRes

data class Chat(
    val id: Int,
    @DrawableRes
    val avatar: Int,
    val title: String,
    val subtitle: String?,
    val text: String,
    @DrawableRes
    val preview: Int?,
    val isScam: Boolean,
    val isVerified: Boolean,
    val isMuted: Boolean,
    val time: String,
    val status: Status,
    val unreadCount: Int,
) {
    enum class Status {
        NONE, CHECKED, READ
    }
}
