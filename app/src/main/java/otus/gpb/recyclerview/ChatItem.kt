package otus.gpb.recyclerview

import androidx.annotation.DrawableRes

data class ChatItem(
    val mainName: String,
    val secondaryName: String?,
    val textMessage: String,
    val textTime: String,
    val isMuted: Boolean,
    val isVerified: Boolean,
    @DrawableRes val messageImg: Int?,
    @DrawableRes val stateMessage: Int,
    val isFavour: Boolean,
    @DrawableRes val mainImg: Int
)
