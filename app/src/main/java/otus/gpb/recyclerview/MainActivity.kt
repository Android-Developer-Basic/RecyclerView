package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

class MainActivity : AppCompatActivity() {

    private val listView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val chatItems = getChatItems()
    private val adapter: ChatAdapter = ChatAdapter()
    private val pageSize = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView.recycledViewPool.setMaxRecycledViews(0, 15)
        listView.addItemDecoration(ChatItemDecoration(applicationContext))
        ItemTouchHelper(ChatItemTouchCallback { removeItem(it) }).attachToRecyclerView(listView)
        listView.adapter = adapter
        adapter.submitList(chatItems.slice(0 until pageSize))
        listView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as? LinearLayoutManager)?.apply {
                    findLastVisibleItemPosition()
                        .takeIf { it == itemCount - 1 && itemCount < chatItems.size }
                        ?.let {
                            adapter.submitList(chatItems.slice(0 until itemCount + pageSize))
                        }
                }
            }
        })
    }

    private fun removeItem(position: Int) {
        chatItems.removeAt(position)
        adapter.submitList(chatItems.slice(0 until adapter.itemCount - 1))
    }

    private fun getChatItems(): MutableList<Chat> = mutableListOf(
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
    ).apply {
        (11..60).forEach {
            add(
                Chat(
                    title = "Item $it",
                    text = "Yes, they are necessary",
                    dateTimeText = "11:38 AM",
                    muted = true
                )
            )
        }
    }
}