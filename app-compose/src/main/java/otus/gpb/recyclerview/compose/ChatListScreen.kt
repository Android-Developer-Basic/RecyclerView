package otus.gpb.recyclerview.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import otus.gpb.recyclerview.core.Chat
import otus.gpb.recyclerview.core.MessageStatus
import otus.gpb.recyclerview.core.R as CoreR

private const val LOAD_THRESHOLD = 5
private const val LOAD_DELAY_MS = 500L

private val AvatarColors = listOf(
    Color(0xFFE17076), Color(0xFFFAA774), Color(0xFFA695E7), Color(0xFF7BC862),
    Color(0xFF6EC9CB), Color(0xFF65AADD), Color(0xFFEE7AAE),
)

@Composable
fun ChatListScreen(modifier: Modifier = Modifier) {
    val state = rememberSaveable(saver = ChatListState.Saver) { ChatListState() }
    val listState = rememberLazyListState()

    // Подгрузка: когда до конца списка остаётся меньше LOAD_THRESHOLD ячеек
    LaunchedEffect(state, listState) {
        fun lastVisibleToSize() = (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1) to state.chats.size
        snapshotFlow { lastVisibleToSize() }
            .filter { (last, size) -> last >= size - LOAD_THRESHOLD }
            .collect {
                // Имитация сетевой задержки; за это время список мог измениться, поэтому проверяем ещё раз
                delay(LOAD_DELAY_MS)
                val (last, size) = lastVisibleToSize()
                if (last >= size - LOAD_THRESHOLD) state.loadMore()
            }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(CoreR.color.chat_background)),
    ) {
        items(state.chats, key = { it.id }) { chat ->
            // rememberUpdatedState: колбэк состояния свайпа создаётся один раз, а чат в ячейке может смениться
            val currentChat by rememberUpdatedState(chat)
            SwipeToRemoveItem(
                onRemove = { state.remove(currentChat) },
                modifier = Modifier.animateItem(),
            ) {
                ChatItem(chat)
            }
        }
    }
}

/**
 * Свайп влево: под ячейкой открывается красная плашка «Remove», при полном свайпе ячейка удаляется
 */
@Composable
private fun SwipeToRemoveItem(
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            (value == SwipeToDismissBoxValue.EndToStart).also { if (it) onRemove() }
        },
        positionalThreshold = { totalDistance -> totalDistance * 0.5f },
    )
    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(CoreR.color.chat_swipe_background)),
                    contentAlignment = Alignment.TopEnd,
                ) {
                    // Иконка и подпись стоят у правого края и открываются вместе с плашкой
                    Column(
                        modifier = Modifier
                            .width(86.dp)
                            .padding(top = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            painter = painterResource(CoreR.drawable.ic_delete),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified,
                        )
                        Text(
                            text = stringResource(CoreR.string.remove),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        },
    ) {
        // Фон нужен, чтобы сдвигаемая ячейка закрывала плашку
        Column(modifier = Modifier.background(colorResource(CoreR.color.chat_background))) {
            content()
            HorizontalDivider(
                modifier = Modifier.padding(start = 73.dp),
                thickness = 1.dp,
                color = colorResource(CoreR.color.chat_divider),
            )
        }
    }
}

@Composable
private fun ChatItem(chat: Chat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 10.dp, end = 13.dp),
    ) {
        Avatar(chat, modifier = Modifier.padding(top = 11.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, end = 8.dp, top = 8.dp),
        ) {
            TitleRow(chat)
            val author = chat.author
            if (author != null) {
                Text(
                    text = author,
                    modifier = Modifier.padding(top = 1.dp),
                    color = colorResource(CoreR.color.chat_text_primary),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            MessageRow(chat, modifier = Modifier.padding(top = if (author != null) 2.dp else 3.dp))
        }

        // Время сверху, значки под ним — у правого края
        Box(modifier = Modifier.fillMaxHeight()) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 11.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (chat.status != MessageStatus.NONE) {
                    Icon(
                        painter = painterResource(
                            if (chat.status == MessageStatus.READ) CoreR.drawable.ic_double_check else CoreR.drawable.ic_check,
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 2.dp)
                            .size(18.dp),
                        tint = Color.Unspecified,
                    )
                }
                Text(
                    text = chat.time,
                    color = colorResource(CoreR.color.chat_text_secondary),
                    fontSize = 13.sp,
                    maxLines = 1,
                )
            }
            Badges(
                chat,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 43.dp),
            )
        }
    }
}

@Composable
private fun Avatar(chat: Chat, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(57.dp)
            .clip(CircleShape)
            .background(AvatarColors[(chat.id % AvatarColors.size).toInt()]),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = initialsOf(chat.title),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
        )
    }
}

private fun initialsOf(title: String): String = title
    .split(' ')
    .filter { it.isNotBlank() }
    .take(2)
    .joinToString("") { it.first().uppercase() }

/**
 * Заголовок и значки за ним; заголовок сжимается с многоточием, значки остаются на месте
 */
@Composable
private fun TitleRow(chat: Chat) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = chat.title,
            modifier = Modifier.weight(1f, fill = false),
            color = colorResource(CoreR.color.chat_text_primary),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (chat.isMuted) {
            Icon(
                painter = painterResource(CoreR.drawable.ic_mute),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(18.dp),
                tint = Color.Unspecified,
            )
        }
        if (chat.isVerified) {
            Icon(
                painter = painterResource(CoreR.drawable.ic_verified),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(18.dp),
                tint = Color.Unspecified,
            )
        }
        if (chat.isScam) {
            Text(
                text = stringResource(CoreR.string.scam),
                modifier = Modifier
                    .padding(start = 6.dp)
                    .border(BorderStroke(1.dp, colorResource(CoreR.color.chat_scam)), RoundedCornerShape(3.dp))
                    .padding(horizontal = 4.dp),
                color = colorResource(CoreR.color.chat_scam),
                fontSize = 11.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun MessageRow(chat: Chat, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val previewColor = chat.previewColor
        if (previewColor != null) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(previewColor)),
            )
            Spacer(Modifier.width(6.dp))
        }
        Text(
            text = chat.message,
            color = colorResource(CoreR.color.chat_text_secondary),
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun Badges(chat: Chat, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (chat.hasMention) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(colorResource(CoreR.color.chat_accent), CircleShape)
                    .padding(4.dp),
            ) {
                Icon(
                    painter = painterResource(CoreR.drawable.ic_mention),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Unspecified,
                )
            }
        }
        if (chat.isPinned && chat.unreadCount == 0) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(BorderStroke(1.5.dp, colorResource(CoreR.color.chat_icon_secondary)), CircleShape)
                    .padding(4.dp),
            ) {
                Icon(
                    painter = painterResource(CoreR.drawable.ic_pin),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(45f),
                    tint = Color.Unspecified,
                )
            }
        }
        if (chat.unreadCount > 0) {
            val color = if (chat.isMuted) CoreR.color.chat_counter_muted else CoreR.color.chat_accent
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .defaultMinSize(minWidth = 24.dp)
                    .background(colorResource(color), RoundedCornerShape(12.dp))
                    .padding(horizontal = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = chat.unreadCount.toString(),
                    color = Color.White,
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}
