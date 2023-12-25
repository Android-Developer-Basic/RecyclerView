package otus.gpb.recyclerview.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import otus.gpb.recyclerview.data.RepositoryImpl
import otus.gpb.recyclerview.data.model.Chat
import otus.gpb.recyclerview.domain.DeleteItemUseCase
import otus.gpb.recyclerview.domain.GetChatsUseCase
import otus.gpb.recyclerview.domain.LoadDataUseCase
import kotlin.concurrent.thread

class MainViewModel : ViewModel() {
    private val repository = RepositoryImpl
    private val getChatsUseCase = GetChatsUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    var chats = getChatsUseCase.get()


    fun deleteChat(chat: Chat){
        deleteItemUseCase.deleteItem(chat)
    }

    fun loadData(){
        //загрзка данных
        thread {
            Thread.sleep(2000)
            loadDataUseCase.load(10)
        }

    }



}