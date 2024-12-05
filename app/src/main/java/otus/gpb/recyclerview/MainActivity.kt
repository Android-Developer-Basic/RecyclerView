package otus.gpb.recyclerview

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.callback.SwipeCallBack
import otus.gpb.recyclerview.converter.ContactConverter
import otus.gpb.recyclerview.databinding.ActivityMainBinding
import otus.gpb.recyclerview.model.ContactViewModel

class MainActivity : AppCompatActivity() {

    val viewModel = ContactViewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var converter: ContactConverter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribe()
    }

    fun subscribe() {
        viewModel.contacts.observe(this) { contacts ->
            // Update UI with the new count value
            converter.addList(contacts)
        }

        configureRecycler()
        setupSwipe(binding.recyclerView)
        viewModel.load()
    }

    fun configureRecycler() {
        converter = ContactConverter()
        binding.recyclerView.addItemDecoration(getListRecyclerDecoration())
        binding.recyclerView.adapter = converter
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
                    viewModel.load()
                }
            }
        })

    }

    private fun setupSwipe(recyclerView: RecyclerView) {
        val iconMarginDpRight = 29
        val iconMarginDpTop = 16
        val background = Paint()
        val icon = getDrawable(R.drawable.archive_24px)

        background.color = getColor(R.color.backgroundDelete)
        val callback = SwipeCallBack(
            density = resources.displayMetrics.density,
            scaledDensity = resources.displayMetrics.scaledDensity,
            iconMarginDpRight = iconMarginDpRight,
            iconMarginDpTop = iconMarginDpTop,
            swipeAction = {
                val position = it.adapterPosition
                converter.remove(position)
            },
            background = background,
            icon = icon
        )
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun getListRecyclerDecoration(): RecyclerView.ItemDecoration {
        val dividerDrawable =
            AppCompatResources.getDrawable(this, R.drawable.separator)
        return DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            dividerDrawable?.let {
                setDrawable(it)
            }
        }
    }
}