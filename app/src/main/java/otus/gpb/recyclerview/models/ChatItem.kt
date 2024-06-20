package otus.gpb.recyclerview.models

import androidx.annotation.DrawableRes
import java.util.Date


enum class ChatStatus(val value: String) {
    Scam("SCAM"),
    Progress("PROGRESS"),
    None("none");

    companion object {
        public fun fromValue(value: String): ChatStatus = when (value) {
            "SCAM" -> Scam
            "PROGRESS"  -> Progress
            "none"  -> None
            else         -> throw IllegalArgumentException()
        }
    }
}

data class LastMessage(
    val title: String,
    val hasImg: Boolean,
)

data class ChatItem(
    val id: String,
    @DrawableRes val imgRes: Int,
    val title: String,
    val subject: String,
    val isMuted: Boolean,
    val lastMessage: LastMessage?,
    val unReadCount: Int,
    val isMessageSend: Boolean,
    val isMessageRead: Boolean,
    val date: Date,
    val status: ChatStatus,
)