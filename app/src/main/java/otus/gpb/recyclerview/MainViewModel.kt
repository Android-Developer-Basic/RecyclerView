package otus.gpb.recyclerview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel constructor(
  private val application: Application,
) : ViewModel() {
  private var fetchJob: Job? = null

  private val chatItemsRepository: ChatItemsRepository =
    (application as RecyclerViewApplication).getChatItemsRepository()

  private val mutableChatItems = MutableStateFlow<List<ChatItem>>(emptyList())
  val chatItems: Flow<List<ChatItem>>
    get() = mutableChatItems

  init {
    fetchChatItems(
      ChatItemsRepository.FetchParams(
        fromIndex = 0,
        amount = DEFAULT_FETCH_ITEMS_AMOUNT,
      )
    )
  }

  override fun onCleared() {
    super.onCleared()
    fetchJob?.cancel()
  }

  fun fetchNextChatItems() {
    fetchChatItems(
      ChatItemsRepository.FetchParams(
        fromIndex = mutableChatItems.value.size,
        amount = DEFAULT_FETCH_ITEMS_AMOUNT,
      )
    )
  }

  private fun fetchChatItems(params: ChatItemsRepository.FetchParams) {
    fetchJob?.cancel()
    fetchJob = viewModelScope.launch {
      chatItemsRepository
        .fetchChatItems(
          params = params,
        )
        .collect { result ->
          mutableChatItems.update {
            it + result.items
          }
        }
    }
  }

  companion object {
    private const val DEFAULT_FETCH_ITEMS_AMOUNT = 10
  }
}