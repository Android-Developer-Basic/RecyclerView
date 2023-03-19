package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.ChatList.pageIndex
import otus.gpb.recyclerview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter
    private var mItemTouchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChatAdapter()
        binding.recyclerView.adapter = adapter // Назначение адаптера для RecyclerView

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val callback: ItemTouchHelper.Callback = SwipeHelperCallback(adapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper?.attachToRecyclerView(binding.recyclerView)

        binding.recyclerView.setOnScrollChangeListener { recyclerView, p1, p2, p3, p4 ->
            val  layoutManager = (recyclerView as RecyclerView).layoutManager
            val  visibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (visibleItem == layoutManager.itemCount - 1 ){
                if(pageIndex + 10 <= ChatList.list.size) {
                    adapter.addItem(ChatList.list.subList(pageIndex, pageIndex + 10))
                    pageIndex += 10
                    print(pageIndex)
                }
            }
        }


    }


}
