package otus.gpb.recyclerview.domain

import otus.gpb.recyclerview.data.model.Chat

class DeleteItemUseCase(val repository: Repository) {
    fun deleteItem(chat: Chat){
        repository.deleteItem(chat)
    }
}