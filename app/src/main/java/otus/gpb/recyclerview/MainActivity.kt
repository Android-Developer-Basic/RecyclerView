package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ChatAdapter
    private var isLoading = false
    private var nextPage = 0
    private val deletedChatIds = mutableSetOf<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextPage = savedInstanceState?.getInt(STATE_NEXT_PAGE) ?: 0
        savedInstanceState?.getLongArray(STATE_DELETED_IDS)?.forEach(deletedChatIds::add)

        adapter = ChatAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            ChatDividerDecoration(
                dividerColor = ContextCompat.getColor(this, R.color.divider),
                startInset = resources.getDimensionPixelSize(R.dimen.chat_divider_inset),
                height = resources.getDimensionPixelSize(R.dimen.chat_divider_height),
            ),
        )

        val swipeCallback = ChatSwipeCallback(this) { chatId ->
            deletedChatIds += chatId
            adapter.submitList(adapter.currentList.filterNot { it.id == chatId })
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0 || isLoading) return
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                if (lastVisible >= adapter.itemCount - PAGINATION_THRESHOLD) {
                    loadNextPage()
                }
            }
        })

        if (nextPage == 0) {
            loadNextPage { ensureViewportFilled(recyclerView) }
        } else {
            adapter.submitList(
                (0 until nextPage)
                    .flatMap { ChatFactory.createPage(it) }
                    .filterNot { it.id in deletedChatIds },
            ) { ensureViewportFilled(recyclerView) }
        }
    }

    private fun loadNextPage(onCommitted: (() -> Unit)? = null) {
        if (isLoading) return
        isLoading = true
        val newItems = ChatFactory.createPage(nextPage++).filterNot { it.id in deletedChatIds }
        adapter.submitList(adapter.currentList + newItems) {
            isLoading = false
            onCommitted?.invoke()
        }
    }

    private fun ensureViewportFilled(recyclerView: RecyclerView) {
        recyclerView.post {
            if (!recyclerView.canScrollVertically(1) && adapter.itemCount > 0) {
                loadNextPage { ensureViewportFilled(recyclerView) }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_NEXT_PAGE, nextPage)
        outState.putLongArray(STATE_DELETED_IDS, deletedChatIds.toLongArray())
        super.onSaveInstanceState(outState)
    }

    private companion object {
        const val PAGINATION_THRESHOLD = 4
        const val STATE_NEXT_PAGE = "next_page"
        const val STATE_DELETED_IDS = "deleted_chat_ids"
    }
}