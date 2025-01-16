package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.chat.ChatListAdapter
import otus.gpb.recyclerview.chat.ChatTouchHelper

class MainActivity : AppCompatActivity() {

    private val chatAdapter: ChatListAdapter by lazy { ChatListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createRcViewChat()
    }

    private fun createRcViewChat() {
        val cardsMessages = findViewById<RecyclerView>(R.id.cardsMessages)
        cardsMessages.adapter = chatAdapter
        chatAdapter.createChatCards()

        cardsMessages.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        ItemTouchHelper(ChatTouchHelper(chatAdapter, this)).attachToRecyclerView(cardsMessages)
    }
}