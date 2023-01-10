package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import otus.gpb.recyclerview.adapters.TelegramAdapter
import otus.gpb.recyclerview.databinding.ActivityMainBinding
import otus.gpb.recyclerview.utils.TestDataGenerator

class MainActivity : AppCompatActivity() {

    private var isLoading = false
    private lateinit var binding: ActivityMainBinding
    private var rvAdapter = TelegramAdapter()
    private var testdataGenerator = TestDataGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        val testData = testdataGenerator.generateData(11)
        rvAdapter.addItems(testData)
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            adapter = rvAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(applicationContext, RecyclerView.VERTICAL)
            dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider))
            addItemDecoration(dividerItemDecoration)

            addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemsCount = (layoutManager as LinearLayoutManager).childCount
                    val lastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val totalItemsCount = (layoutManager as LinearLayoutManager).itemCount

                    if (!isLoading && dy > 0) {
                        if (lastVisibleItem + visibleItemsCount >= totalItemsCount) {
                            isLoading = true
                            fetchMore()
                            isLoading = false
                        }
                    }
                }
            })

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(-1, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    rvAdapter.removeItem(viewHolder.layoutPosition)
                }
            }).attachToRecyclerView(this)
        }
    }

    private fun fetchMore() {
        val list = testdataGenerator.generateData(10)
        rvAdapter.addItems(list)
    }
}