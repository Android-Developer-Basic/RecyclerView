package otus.gpb.recyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel(), Listener {
    val chatAdapter = ChatAdapter(this)

    private var items: MutableList<ChatListItem> = chatMessages.toMutableList()
    private var isDataLoading: Boolean = false

    fun setupData() {
        chatAdapter.setItems(items)
    }

    fun removeItem(id: Int) {
        items.removeAt(id)
        setupData()
    }

    fun loadNewData() {
        if (isDataLoading) { return }

        isDataLoading = true

        viewModelScope.launch {
            items.add(ChatListItem.LoadItem())
            setupData()

            delay(3000L)

            items.removeLast()
            items.addAll(chatMessages)
            setupData()
            isDataLoading = false
        }
    }

    override fun onItemClicked(id: Int) { }
}