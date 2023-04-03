package otus.gpb.recyclerview

import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration


class MainActivity : AppCompatActivity() {

    private val data = ListGetter().getList()

    private val chatAdapter: ChatAdapter by lazy {
        ChatAdapter()
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this@MainActivity)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = chatAdapter
        chatAdapter.submitData(data)

        //pagination
        recyclerView.addOnScrollListener(object : PageScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMoreItems()
            }
        })

        //item decoration
        val dividerItemDecoration = MaterialDividerItemDecoration(
            recyclerView.context,
            VERTICAL
        )
        dividerItemDecoration.dividerInsetStart = 220
        recyclerView.addItemDecoration(dividerItemDecoration)

        //swipe-to-dismiss
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.START)
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

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val viewTarget = (viewHolder as ChatViewHolder).returnUpperView()
                getDefaultUIUtil().onDraw(
                    c, recyclerView, viewTarget, dX, dY,
                    actionState, isCurrentlyActive
                )
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                val viewTarget = (viewHolder as ChatViewHolder).returnUpperView()
                getDefaultUIUtil().clearView(viewTarget)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    // for pagination
    private fun getMoreItems() {
        isLoading = false
        chatAdapter.addData(ListGetter().getList())
    }
}

