package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  private val viewModel: MainViewModel by lazy { MainViewModel(application) }

  private val listView: RecyclerView by lazy { findViewById(R.id.recyclerView) }

  private val adapter by lazy { ChatItemsAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    listView.addItemDecoration(getListItemDecoration())
    listView.adapter = adapter

    ItemTouchHelper(ChatItemTouchCallback()).attachToRecyclerView(listView)
    initChatItemsSubscription()
  }

  private fun getListItemDecoration(): RecyclerView.ItemDecoration {
    val dividerDrawable =
      AppCompatResources.getDrawable(this, R.drawable.chat_item_divider)
    return DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
      dividerDrawable?.let {
        setDrawable(it)
      }
    }
  }

  private fun initChatItemsSubscription() {
    lifecycleScope.launch {
      viewModel.chatItems.collect { result ->
        adapter.setItems(result)
      }
    }
  }
}