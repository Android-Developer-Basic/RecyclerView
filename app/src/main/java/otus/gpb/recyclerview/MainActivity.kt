package otus.gpb.recyclerview

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.Adapters.ChatAdapter
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribe()
    }

    fun subscribe() {
        viewModel.chats.observe(this) { chats ->
            // Update UI with the new count value
            adapter.addList(chats)
        }

        configureRecycler()
        viewModel.loadData()
    }

    fun configureRecycler() {
        adapter = ChatAdapter()
        binding.recyclerView.addItemDecoration(getListRecyclerDecoration())
        binding.recyclerView.adapter = adapter
    }

    private fun getListRecyclerDecoration(): RecyclerView.ItemDecoration {
        val dividerDrawable =
            AppCompatResources.getDrawable(this, R.drawable.chat_divider)
        return DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            dividerDrawable?.let {
                setDrawable(it)
            }
        }
    }
}