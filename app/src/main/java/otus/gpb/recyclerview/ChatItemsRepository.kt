package otus.gpb.recyclerview

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random


interface ChatItemsRepository {
  fun fetchChatItems(): Flow<List<ChatItem>>
}

class ChatItemsRepositoryImpl : ChatItemsRepository {
  private val cacheItems: List<ChatItem>

  private val avatarImgResSet = arrayListOf(
    R.drawable.avatar1,
    R.drawable.avatar2,
    R.drawable.avatar3,
    R.drawable.avatar4,
    R.drawable.avatar5,
    R.drawable.avatar6,
  )
  private val titleSet = arrayListOf(
    "Pizza",
    "Elon",
    "Pasha",
    "Telegram Support",
    "Karina",
    "Marilyn",
  )

  init {
    cacheItems = generateChatItems()

    println("TEST inited cacheItems: $cacheItems")
  }

  override fun fetchChatItems(): Flow<List<ChatItem>> = flow {
    emit(cacheItems)
  }.flowOn(Dispatchers.IO)

  private fun generateChatItems(): List<ChatItem> = mutableListOf<ChatItem>()
    .apply {
      for (i in 0..50) {
        val randomIndex = (0..10).random()
        val imgRes =
          if (randomIndex < avatarImgResSet.size) avatarImgResSet[randomIndex] else R.drawable.avatar4
        val title =
          if (randomIndex < titleSet.size) titleSet[randomIndex] else "Chat $i"

        val lastMessage = if (Random.nextBoolean()) {
          ChatItem.LastMessage(
            author = if (Random.nextBoolean()) null else "Author $i",
            title = "How are u?",
            hasImg = Random.nextBoolean(),
          )
        } else null

        add(
          ChatItem(
            id = "$i",
            imgRes = imgRes,
            title = title,
            isVerified = Random.nextBoolean(),
            isMuted = Random.nextBoolean(),
            isScam = Random.nextBoolean(),
            lastMessage = lastMessage,
            isMessageSend = Random.nextBoolean(),
            isMessageRead = Random.nextBoolean(),
            date = SimpleDateFormat(
              "yyyy-MM-dd HH:mm:ss",
              Locale.getDefault()
            ).toPattern(),
            unreadValue = 0,
          )
        )
      }
    }

  companion object {
    private var instance: ChatItemsRepositoryImpl? = null

    fun getInstance(): ChatItemsRepository =
      instance ?: ChatItemsRepositoryImpl().also {
        instance = it
      }
  }
}