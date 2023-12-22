package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val listView: RecyclerView by lazy { findViewById(R.id.recyclerView) }

    private val adapter: ChatAdapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.addItemDecoration(ChatItemDecoration(applicationContext))
        ItemTouchHelper(ChatItemTouchCallback()).attachToRecyclerView(listView)
        listView.adapter = adapter
        adapter.submitList(fillChat())
    }

    private fun fillChat(): List<Chat> = listOf(
        Chat(
            title = "Pizza",
            subtitle = "jija",
            text = "Yes, they are necessary",
            dateTimeText = "11:38 AM",
            muted = true
        ),
        Chat(
            title = "Elon",
            text = "I love /r/Reddit!",
            dateTimeText = "12:44 AM",
            muted = true
        ),
        Chat(
            title = "Pasha",
            text = "How are u?",
            dateTimeText = "Fri",
            muted = true,
            verified = true
        ),
        Chat(
            title = "Telegram Support",
            subtitle = "Support",
            text = "Yes it happened.",
            dateTimeText = "Thu",
            verified = true,
            unreadMessages = 1
        ),
        Chat(
            title = "Karina",
            text = "Okay",
            dateTimeText = "Wed",
            sent = true
        ),
        Chat(
            title = "Marylin",
            text = "Will it ever happen",
            dateTimeText = "May 02",
            read = true
        ),
        Chat(
            title = "Item 7",
            text = "Yes, they are necessary",
            dateTimeText = "11:38 AM",
            muted = true
        ),
        Chat(
            title = "Item 8",
            text = "Yes, they are necessary",
            dateTimeText = "11:38 AM",
            muted = true
        ),
        Chat(
            title = "Item 9",
            text = "Yes, they are necessary",
            dateTimeText = "11:38 AM",
            muted = true
        ),
        Chat(
            title = "Item 10",
            text = "Yes, they are necessary",
            dateTimeText = "11:38 AM",
            muted = true
        )
    )
}