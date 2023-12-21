package otus.gpb.recyclerview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import otus.gpb.recyclerview.R
import otus.gpb.recyclerview.data.model.AccountStatus
import otus.gpb.recyclerview.data.model.Chat
import otus.gpb.recyclerview.domain.Repository

object RepositoryImpl : Repository {

    private val chatsLD = MutableLiveData<List<Chat>>()
    private val chats = mutableListOf<Chat>()

    init {
        val countListData = 20
        val chatModel = Chat(
            0,
            "fan avengers",
            "sfdds",
            R.drawable.mstiteli,
            false,
            "Alex: последний фильм был хорош",
            "11:50 AM",
            true,
            0,
            AccountStatus.Group
        )
        for (i in 0 until countListData) {
            chats.add(chatModel.copy(id = i, nameUserInChat = "fan avengers $i"))
        }
        updateList()

    }

    override fun showAllChats(): LiveData<List<Chat>> {
        return chatsLD
    }

    override fun deleteItem(chat: Chat) {
        chats.remove(chat)
/*        for (i in chats.indices) {
            chats[i].id = i
        }*/
        updateList()

    }

    private fun updateList(){
        chatsLD.postValue(chats.toList())
    }



}