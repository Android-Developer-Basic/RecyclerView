package otus.gpb.recyclerview.domain

import androidx.lifecycle.LiveData
import otus.gpb.recyclerview.data.model.Chat

interface Repository {

    fun showAllChats() : LiveData<List<Chat>>

    fun deleteItem(chat: Chat)

}