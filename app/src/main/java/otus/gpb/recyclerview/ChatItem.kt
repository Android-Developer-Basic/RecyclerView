package otus.gpb.recyclerview

import androidx.annotation.DrawableRes

data class ChatItem(
  val id: String,
  @DrawableRes val imgRes: Int,
  val title: String,
  val isVerified: Boolean,
  val isMuted: Boolean,
  val isScam: Boolean,
  val lastMessage: LastMessage?,
  val isMessageSend: Boolean,
  val isMessageRead: Boolean,
  val date: String,
  val unreadValue: Int,
) {
  data class LastMessage(
    val author: String?,
    val title: String,
    val hasImg: Boolean,
  )
}