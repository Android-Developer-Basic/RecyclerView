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
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { ChatAdapter() }
    private val presenter by lazy { Presenter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        adapter.setChats(presenter.getChats())

        recyclerView.addItemDecoration(DeviderDecorator())

        recyclerView.addOnScrollListener(PageScrollListener(layoutManager).apply {
            onLoadMore = {
                Toast.makeText(applicationContext, "Loading new Items", Toast.LENGTH_SHORT).show()
                adapter.setNewChats(presenter.getChats(adapter.itemCount))
                loading = false
            }
        })

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            val context = this@MainActivity.applicationContext

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
            ): Boolean {
                return false
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
                viewHolder.itemView.apply {
                    ContextCompat.getDrawable(context, R.drawable.ic_archive)?.apply {
                        val background = ColorDrawable()
                        background.color = ContextCompat.getColor(context, R.color.telegram)

                        val iconMargin = ((bottom - top) - intrinsicHeight) / 2
                        val iconTop = top + iconMargin
                        val iconBottom = iconTop + intrinsicHeight
                        val iconRight = right - iconMargin
                        val iconLeft = iconRight - intrinsicWidth

                        setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        background.setBounds(right + dX.toInt(), top, right, bottom)

                        background.draw(c)
                        draw(c)
                    }
                }

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

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return 0.2f
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.deleteChat(viewHolder.adapterPosition)
            }

        }).attachToRecyclerView(recyclerView)

    }
}