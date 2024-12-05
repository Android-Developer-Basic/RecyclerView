package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val chatAdapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = chatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ChatItemDecoration(this, LinearLayoutManager.VERTICAL))
        ItemTouchHelper(SwipeToDeleteCallback(chatAdapter)).attachToRecyclerView(recyclerView)
        chatAdapter.addItems(ChatGenerator.generateItems())
        chatAdapter.onLoadMore = {
            chatAdapter.addItems(ChatGenerator.generateItems())
        }
    }
}