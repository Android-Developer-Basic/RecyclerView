package otus.gpb.recyclerview

import android.app.Activity
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
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
        setupSwipe(binding.recyclerView)
        viewModel.loadData()
    }

    fun configureRecycler() {
        adapter = ChatAdapter()
        binding.recyclerView.addItemDecoration(getListRecyclerDecoration())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.loadData()
                }
            }
        })

    }

    private fun setupSwipe(recyclerView: RecyclerView) {
        val iconMarginDpRight = 29
        val iconMarginDpTop = 16
        val background = Paint()
        val icon = getDrawable(R.drawable.arcive_icon)

        background.color = getColor(R.color.backgroundDelete)
        val callback = SwipeCallBack(
            density = resources.displayMetrics.density,
            scaledDensity = resources.displayMetrics.scaledDensity,
            iconMarginDpRight = iconMarginDpRight,
            iconMarginDpTop = iconMarginDpTop,
            swipeAction = {
                val position = it.adapterPosition
                adapter.remove(position)
            },
            background = background,
            icon = icon
        )
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
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