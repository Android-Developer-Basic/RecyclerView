package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val chatAdapter: ChatAdapter by lazy {
        ChatAdapter()
    }
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this@MainActivity)
        val paging = PageScrollListener(layoutManager).apply{
            this.setOnLoadMoreListener { loadMoreData() }
        }
        recyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = chatAdapter
            this.addItemDecoration(DividerItemDecoration())
            this.addOnScrollListener(paging)
        }
        chatAdapter.setItems(Chat().fillData())
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(chatAdapter, resources))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun loadMoreData() {
        chatAdapter.addItems(Chat().fillData())
    }
}