package otus.gpb.recyclerview.domain

import androidx.lifecycle.LiveData
import otus.gpb.recyclerview.data.model.Chat

class GetChatsUseCase(val repository: Repository) {
    fun get(): LiveData<List<Chat>>  = repository.showAllChats()
}