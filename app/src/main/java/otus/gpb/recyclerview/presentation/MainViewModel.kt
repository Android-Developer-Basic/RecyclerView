package otus.gpb.recyclerview.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import otus.gpb.recyclerview.data.RepositoryImpl
import otus.gpb.recyclerview.data.model.Chat
import otus.gpb.recyclerview.domain.DeleteItemUseCase
import otus.gpb.recyclerview.domain.GetChatsUseCase

class MainViewModel : ViewModel() {
    private val repository = RepositoryImpl
    private val getChatsUseCase = GetChatsUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)

    var chats = getChatsUseCase.get()
    fun deleteChat(chat: Chat){
        deleteItemUseCase.deleteItem(chat)
    }

}