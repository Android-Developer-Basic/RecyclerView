package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var list: MutableList<ChatItem>
    private val adapter by lazy { ChatAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(this, R.drawable.divider)
                ?: error("Not divider drawable")
        )

        recyclerView.addItemDecoration(divider)

        ItemTouchHelper(ItemTouchHelperCallback()).attachToRecyclerView(recyclerView)

        list = ChatDataGenerator.generateChatItemsList(20)

        recyclerView.adapter = adapter

        adapter.submitList(list)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem + 1 == totalItemCount) {
                    adapter.addItems(5)
                }
            }
        })
    }
}