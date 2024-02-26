package otus.gpb.recyclerview

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.random.Random

interface ChatItemsRepository {
  fun fetchChatItems(params: FetchParams): Flow<ChatItems>

  data class FetchParams(
    val fromIndex: Int,
    val amount: Int,
  )

  data class ChatItems(
    val items: List<ChatItem>,
    val total: Int,
  )
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

  override fun fetchChatItems(params: ChatItemsRepository.FetchParams): Flow<ChatItemsRepository.ChatItems> =
    flow {
      kotlinx.coroutines.delay(1000L)
      val fromIndex = Math.min(params.fromIndex, TOTAL_GENERATED_ITEMS)
      val toIndex =
        Math.min(fromIndex + params.amount - 1, TOTAL_GENERATED_ITEMS)

      if (fromIndex == TOTAL_GENERATED_ITEMS) {
        emit(
          ChatItemsRepository.ChatItems(
            items = emptyList(),
            total = TOTAL_GENERATED_ITEMS,
          )
        )
        return@flow
      }

      val nextItems = cacheItems.slice(fromIndex..toIndex)

      emit(
        ChatItemsRepository.ChatItems(
          items = nextItems,
          total = TOTAL_GENERATED_ITEMS,
        )
      )
    }.flowOn(Dispatchers.IO)

  private fun generateChatItems(): List<ChatItem> = mutableListOf<ChatItem>()
    .apply {
      for (i in 0..TOTAL_GENERATED_ITEMS) {
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
    private const val TOTAL_GENERATED_ITEMS = 100
    private var instance: ChatItemsRepositoryImpl? = null

    fun getInstance(): ChatItemsRepository =
      instance ?: ChatItemsRepositoryImpl().also {
        instance = it
      }
  }
}