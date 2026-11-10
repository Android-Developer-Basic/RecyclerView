package otus.gpb.recyclerview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import otus.gpb.recyclerview.core.Chat
import otus.gpb.recyclerview.core.ChatRepository

class MainActivity : AppCompatActivity() {

    private val adapter = ChatAdapter()
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var repository: ChatRepository
    private lateinit var layoutManager: LinearLayoutManager

    private var chats: List<Chat> = emptyList()
    private val removedIds = mutableSetOf<Long>()
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(ChatDividerDecoration(this))
        ItemTouchHelper(SwipeToRemoveCallback(this, ::removeChat)).attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                loadMoreIfNeeded()
            }
        })

        if (savedInstanceState == null) {
            repository = ChatRepository()
            showChats(repository.loadPage())
        } else {
            restoreChats(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем не сами чаты, а то, из чего их можно однозначно пересоздать
        outState.putString(KEY_BASE_TIME, repository.baseTime.toString())
        outState.putLong(KEY_LOADED_COUNT, repository.loadedCount)
        outState.putLongArray(KEY_REMOVED_IDS, removedIds.toLongArray())
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun showChats(newChats: List<Chat>) {
        chats = newChats
        // После применения диффа проверяем: если список стал короче экрана, подгружаем ещё
        adapter.submitList(chats) { loadMoreIfNeeded() }
    }

    private fun restoreChats(state: Bundle) {
        repository = ChatRepository(LocalDateTime.parse(requireNotNull(state.getString(KEY_BASE_TIME))))
        state.getLongArray(KEY_REMOVED_IDS)?.let { removedIds += it.toList() }
        // Список уходит в пустой адаптер синхронно — до первой раскладки, поэтому RecyclerView восстановит скролл
        showChats(repository.restore(state.getLong(KEY_LOADED_COUNT)).filterNot { it.id in removedIds })
    }

    private fun removeChat(position: Int) {
        val removed = adapter.currentList[position]
        removedIds += removed.id
        showChats(chats.filterNot { it.id == removed.id })
    }

    private fun loadMoreIfNeeded() {
        if (isLoading) return
        if (layoutManager.findLastVisibleItemPosition() < adapter.itemCount - LOAD_THRESHOLD) return

        isLoading = true
        // Имитация сетевой задержки
        handler.postDelayed({
            isLoading = false
            showChats(chats + repository.loadPage())
        }, LOAD_DELAY_MS)
    }

    private companion object {
        const val LOAD_THRESHOLD = 5
        const val LOAD_DELAY_MS = 500L

        const val KEY_BASE_TIME = "base_time"
        const val KEY_LOADED_COUNT = "loaded_count"
        const val KEY_REMOVED_IDS = "removed_ids"
    }
}
