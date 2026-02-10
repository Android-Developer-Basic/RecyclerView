package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MessagesAdapterListener {

    private lateinit var recycleView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val messagesAdapter by lazy { MessagesAdapter() }
    private val messagesListDataSource = DataSource()

    private val pageSize = 7
    private var currentPage = 0
    private var isLastPage = false

    private lateinit var scrollListener: ScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)

        recycleView.layoutManager = layoutManager
        recycleView.adapter = messagesAdapter
        recycleView
            .addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )

        ItemTouchHelper(ItemTouchHelperCallback(this))
            .attachToRecyclerView(recycleView)

        loadFirstPage()

        scrollListener = ScrollListener(layoutManager) {
            loadNextPage()
        }

        recycleView.addOnScrollListener(scrollListener)

    }

    private fun loadFirstPage() {
        val firstPage = messagesListDataSource.getMessages(page = currentPage, pageSize = pageSize)
        messagesAdapter.submitList(firstPage)
        currentPage++
    }

    private fun loadNextPage() {
        if (isLastPage) return

        val nextPage = messagesListDataSource.getMessages(page = currentPage, pageSize = pageSize)

        if (nextPage.isEmpty()) {
            isLastPage = true
            scrollListener.onLoadFinished()
            return
        }

        val newList = messagesAdapter.currentList.toMutableList()
        newList.addAll(nextPage)
        messagesAdapter.submitList(newList)
        currentPage++

        scrollListener.onLoadFinished()
    }

    override fun onItemSwipe(position: Int) {
        val newList = messagesAdapter.currentList.toMutableList()
        newList.removeAt(position)
        messagesAdapter.submitList(newList)
    }
}