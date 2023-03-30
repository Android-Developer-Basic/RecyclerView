package otus.gpb.recyclerview

import androidx.annotation.DrawableRes

data class Chat(
    @DrawableRes val avatarImage: Int,
    val dialogName: String,
    val messageAuthor: String,
    val message: String,
    val verified: Boolean,
    val sendDate: String,
    val newMessageCount: Int,
    val messageStatus: Status,
    val mutedStatus: Boolean
)

enum class Status(val res: Int) {
    UNREAD(0),
    SENDING(R.drawable.ic_baseline_done_24),
    DELIVERED(R.drawable.ic_baseline_done_24),
    READ(R.drawable.ic_baseline_done_all_24)
}