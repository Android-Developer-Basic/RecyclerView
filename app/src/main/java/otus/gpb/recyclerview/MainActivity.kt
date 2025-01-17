package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var list: MutableList<ChatItem>
    private val adapter by lazy { ChatListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(this, R.drawable.rv_item_divider)
                ?: error("Not divider drawable")
        )

        recyclerView.addItemDecoration(divider)
        ItemTouchHelper(ItemSwipeHelper(this)).attachToRecyclerView(recyclerView)

        list = ChatItemDataGenerator.generateChatItemsList(20)
        recyclerView.adapter = adapter
        adapter.submitList(list)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemCount = adapter.itemCount

                if (lastPosition + 1 >= itemCount) {
                    adapter.addItems(5)
                }
            }
        })
    }
}