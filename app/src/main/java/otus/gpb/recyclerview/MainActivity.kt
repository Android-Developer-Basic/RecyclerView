package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.*

class MainActivity : AppCompatActivity() {

    private val chatAdapter by lazy {
        ChatAdapter()
    }
    private val chatList = FillData().getData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        //val dividerDrawable = AppCompatResources.getDrawable(this@MainActivity, R.drawable.divider)
        val layoutManager = LinearLayoutManager(this@MainActivity)
//        val paging = PageScrollListener(layoutManager).apply {
//            setOnLoadMoreListener {
//                Toast.makeText(this@MainActivity, "Load More", Toast.LENGTH_SHORT).show()
//                val position = dataList.size
//                for (i in 1..10) {
//                    dataList.add(
//                        ItemData(
//                            count = i,
//                            name = "Page Ele$i",
//                            isColored = true
//                        )
//                    )
//                    myAdapter.submitData(dataList)
//                    isLoading = false
//                    myAdapter.notifyItemInserted(position)
//                }
//            }
//        }
        recyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = chatAdapter
            //addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL).apply {
                //dividerDrawable?.let { setDrawable(it) }
            //})
            //addItemDecoration(CustomDividerDecoration())
            //addOnScrollListener(paging)
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                return makeMovementFlags(ItemTouchHelper.DOWN, ItemTouchHelper.START)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                chatAdapter.removeItem(position)
                chatAdapter.notifyItemRemoved(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)


//        for (i in 1..10) {
//            dataList.add(
//                ItemData(
//                    count = i,
//                    name = "Name $i"
//                ).apply {
//                    onClickListener = { item ->
//                        Toast.makeText(this@MainActivity, item.name, Toast.LENGTH_SHORT).show()
//
//                        for (i in 1..item.count) {
//                            dataList.add(
//                                item.count + i,
//                                ItemData(
//                                    count = i,
//                                    name = "New Element $i",
//                                    isColored = true
//                                )
//                            )
//                            myAdapter.submitData(dataList)
//                            myAdapter.notifyItemInserted(item.count)
//                        }
//                    }
//                })
//        }

        chatAdapter.submitData(chatList)
        chatAdapter.notifyDataSetChanged()
    }
}