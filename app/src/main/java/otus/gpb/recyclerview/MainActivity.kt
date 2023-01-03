package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL
import otus.gpb.recyclerview.databinding.ActivityMainBinding
import otus.gpb.recyclerview.service.ChatService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        ChatAdapter(ChatService().getChatList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    private fun setupView() {
        val decorator = MaterialDividerItemDecoration(this, VERTICAL).apply {
            dividerColor = resources.getColor(R.color.dividerColor, theme)
            dividerInsetStart =
                this@MainActivity.resources.getDimensionPixelSize(R.dimen.divider_padding_start)
            isLastItemDecorated = false
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.LEFT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }
        })

        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(decorator)

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val totalItems = recyclerView.layoutManager!!.itemCount
                    val lastVisibleItem =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    if (lastVisibleItem == totalItems - 1) {
                        adapter.addItems()
                    }
                }
            })

            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }
}
