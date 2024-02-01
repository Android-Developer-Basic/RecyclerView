package otus.gpb.recyclerview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel constructor(
  private val application: Application,
) : ViewModel() {
  private val chatItemsRepository: ChatItemsRepository =
    (application as RecyclerViewApplication).getChatItemsRepository()

  private val mutableChatItems = MutableStateFlow<List<ChatItem>>(emptyList())
  val chatItems: Flow<List<ChatItem>>
    get() = mutableChatItems

  init {
    fetchChatItems()
  }

  fun fetchChatItems() {
    viewModelScope.launch {
      chatItemsRepository.fetchChatItems().collect { result ->
        mutableChatItems.update {
          result
        }
      }
    }
  }
}