package otus.gpb.recyclerview.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import otus.gpb.recyclerview.core.Chat
import otus.gpb.recyclerview.core.ChatRepository
import java.time.LocalDateTime

/**
 * Состояние списка чатов для Compose-экрана: загруженные страницы и удалённые свайпом чаты
 */
class ChatListState(
    val baseTime: LocalDateTime = LocalDateTime.now(),
    loadedCount: Long = 0,
    removed: Collection<Long> = emptyList(),
) {
    private val repository = ChatRepository(baseTime)
    private val removedIdSet = removed.toMutableSet()

    val chats = mutableStateListOf<Chat>()
    val loadedCount: Long get() = repository.loadedCount
    val removedIds: Set<Long> get() = removedIdSet

    init {
        if (loadedCount == 0L) {
            chats += repository.loadPage()
        } else {
            chats += repository.restore(loadedCount).filterNot { it.id in removedIdSet }
        }
    }

    fun loadMore() {
        chats += repository.loadPage()
    }

    fun remove(chat: Chat) {
        removedIdSet += chat.id
        chats.removeAll { it.id == chat.id }
    }

    companion object {
        // Как и в MainActivity, сохраняем не сами чаты, а то, из чего их можно однозначно пересоздать
        val Saver: Saver<ChatListState, Any> = listSaver(
            save = { listOf(it.baseTime.toString(), it.loadedCount, ArrayList(it.removedIds)) },
            restore = {
                @Suppress("UNCHECKED_CAST")
                ChatListState(LocalDateTime.parse(it[0] as String), it[1] as Long, it[2] as List<Long>)
            },
        )
    }
}
