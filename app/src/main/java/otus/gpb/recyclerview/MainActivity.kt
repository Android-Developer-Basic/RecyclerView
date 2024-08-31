package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        adapter = ChatAdapter((Chat.chats + Chat.chats).toMutableList())
        binding.recyclerView.adapter = adapter

        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        val divider = AppCompatResources.getDrawable(this, R.drawable.divider)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
                divider?.let { setDrawable(it) }
            }
        )

        ItemTouchHelper(SwipeToDismissCallback(adapter, resources))
            .attachToRecyclerView(binding.recyclerView)

        val paging = ChatScrollListener(layoutManager).apply {
            onLoadMore = {
                adapter.addItems(Chat.chats)
            }
        }
        binding.recyclerView.addOnScrollListener(paging)
    }
}