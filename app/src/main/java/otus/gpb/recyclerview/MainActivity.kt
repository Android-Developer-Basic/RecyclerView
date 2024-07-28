package otus.gpb.recyclerview

import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter
    private var items = GenerateChatItems().getList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ChatAdapter(items)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

        val swipeHandler = object : SwipeCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val divider = AppCompatResources.getDrawable(this, R.drawable.decoration)

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
                divider?.let { setDrawable(it) }
            }
        )

        val paging = PageScrollListener(manager).apply {
            onLoadMore = {
                Toast.makeText(this@MainActivity, "Load more", Toast.LENGTH_LONG).show()
                    val newChatItems = GenerateChatItems().getList().subList(0, 9).toMutableList()
                    items.addAll(newChatItems)
                adapter.notifyDataSetChanged()
                isLoading = false
            }
        }
        recyclerView.addOnScrollListener(paging)
    }
}