package otus.gpb.recyclerview

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val chatAdapter: ChatAdapter by lazy {
        ChatAdapter()
    }
    private  val chatItems = ChatApp().getItems()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
        val paging = PagingRecycler(linearLayoutManager).apply {
            setOnLoadMoreListener {
                val items = mutableListOf<ChatItem>()
                val count = chatItems.size - 1
                for (i in 1..10) {
                    items.add(chatItems[Random.nextInt(count)])
                }
                chatAdapter.submitData(items)
                chatAdapter.notifyItemInserted(items.count())
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = linearLayoutManager
            adapter = chatAdapter

            val divider = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)

            ResourcesCompat.getDrawable(resources, R.drawable.black_line_divider, theme)?.let {
                divider.setDrawable(it)
            }
            addItemDecoration(divider)
            addOnScrollListener(paging)
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                return makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT)
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

                if (direction == ItemTouchHelper.LEFT) {
                    chatAdapter.removeItem(position)
                    chatAdapter.notifyItemRemoved(position)
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.blue_archive))
                    .addActionIcon(R.drawable.archive)
                    .create()
                    .decorate();

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
        chatAdapter.submitData(chatItems)
    }
}