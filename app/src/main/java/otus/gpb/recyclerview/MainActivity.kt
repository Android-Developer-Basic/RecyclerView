package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MainAdapter() }
    private val presenter by lazy { ListChat() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this.applicationContext

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        adapter.setItems(presenter.getList())
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager

        val dividerDrawable = AppCompatResources.getDrawable(this, R.drawable.divider)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            dividerDrawable?.let { setDrawable(it) }
        })

        val paging = PageScrollListener(manager).apply {
            onLoadMore = {
                Toast.makeText(this@MainActivity, "В список добавлено 10 строк", Toast.LENGTH_SHORT).show()
                adapter.addItems(presenter.getList(adapter.itemCount))
                isLoading = false
            }
        }
        recyclerView.addOnScrollListener(paging)

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                return makeMovementFlags(0, ItemTouchHelper.LEFT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                adapter.removeItem(position)
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
                val itemView = viewHolder.itemView
                val itemHeight = itemView.bottom - itemView.top

                val icon = ContextCompat.getDrawable(context, R.drawable.archive)
                val background = ColorDrawable()
                background.color = ContextCompat.getColor(context, R.color.main)

                val iconMargin = (itemHeight - icon!!.intrinsicHeight) / 2
                val iconTop = itemView.top + iconMargin
                val iconBottom = iconTop + icon.intrinsicHeight
                val iconRight = itemView.right - iconMargin
                val iconLeft = iconRight - icon.intrinsicWidth

                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)

                background.draw(c)
                icon.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }
}