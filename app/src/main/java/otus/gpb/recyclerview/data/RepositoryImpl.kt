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
    private val countDataInList: Int
        get() {
            return chats.size
        }

    init {
        loadData(20)
    }

    override fun showAllChats(): LiveData<List<Chat>> {
        return chatsLD
    }

    override fun deleteItem(chat: Chat) {
        chats.remove(chat)
        updateList()
    }

    private fun updateList(){
        chatsLD.postValue(chats.toList())
    }


    /**
     * countData - порция подгрузки
     */
    override fun loadData(countData: Int){
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
        val count = countDataInList
        for (i in 0 until countData) {

            chats.add(chatModel.copy(id = i+1+count, nameUserInChat = "fan avengers ${i+1+count}"))
        }
        updateList()
    }


}