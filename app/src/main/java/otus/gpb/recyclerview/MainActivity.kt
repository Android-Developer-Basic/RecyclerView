package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val listView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val chatItems = getChatItems()
    private val adapter: ChatAdapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView.recycledViewPool.setMaxRecycledViews(0, 15)
        listView.adapter = adapter
        adapter.submitList(chatItems.slice(0 until pageSize))
        listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    companion object {
        private const val pageSize = 10
    }
}