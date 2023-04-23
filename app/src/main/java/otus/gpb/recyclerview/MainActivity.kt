package otus.gpb.recyclerview

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ActivityMainBinding

private val chatAdapter: ChatAdapter by lazy { ChatAdapter() }

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataList = mutableListOf<ItemData>()
    private val pageSize = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(this@MainActivity)
        val itemTouchHelper = ItemTouchHelper(object : SwipeCallback() {
           override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                chatAdapter.removeItem(position)
                chatAdapter.notifyItemRemoved(position)
            }
        })
        val paging = PageScrollListener(layoutManager).apply {
            setOnLoadMoreListener {
                Toast.makeText(this@MainActivity, "Load More", Toast.LENGTH_SHORT).show()
                val position = chatAdapter.itemCount
                val newData = mutableListOf<ItemData>()
                    isLoading = false
                    chatAdapter.addData(PopulateData.populateMore(pageSize))
                    chatAdapter.notifyItemRangeInserted(position, pageSize)

            }
        }
        itemTouchHelper.attachToRecyclerView(recyclerView)

        recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = chatAdapter
            addItemDecoration(DividerDecoration())
            addOnScrollListener(paging)
        }

        dataList.addAll(PopulateData.populateMore(pageSize))

        chatAdapter.submitData(dataList)
        chatAdapter.notifyDataSetChanged()
    }
}