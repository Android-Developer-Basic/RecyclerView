package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val chatAdapter: ChatAdapter by lazy {
        ChatAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter

            val divider = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)

            ResourcesCompat.getDrawable(resources, R.drawable.black_line_divider, theme)?.let {
                divider.setDrawable(it)
            }
            addItemDecoration(divider)
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
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
        chatAdapter.submitData(ChatApp().getItems())
    }
}