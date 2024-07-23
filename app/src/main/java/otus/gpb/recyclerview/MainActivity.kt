package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewBinding()
        setupRecyclerView()
        setupSwipeToDelete()
    }

    private fun initializeViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        adapter = ChatAdapter(chatList = Chat.chatList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setChats(Chat.chatList)
        binding.recyclerView.addItemDecoration(CustomItemDecorator())
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter.deleteItem(viewHolder.adapterPosition)
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
                        ContextCompat.getDrawable(context, R.drawable.archive)?.apply {
                            val background = ColorDrawable()
                            background.color = ContextCompat.getColor(context, R.color.blue)

                            val iconMargin = ((bottom - top) - intrinsicHeight) / 2
                            val iconTop = top + iconMargin / 2
                            val iconBottom = iconTop + intrinsicHeight
                            val iconRight = right - iconMargin
                            val iconLeft = iconRight - intrinsicWidth

                            setBounds(iconLeft, iconTop, iconRight, iconBottom)
                            background.setBounds(right + dX.toInt(), top, right, bottom)

                            background.draw(c)
                            draw(c)
                        }
                        val textPaint = Paint().apply {
                            color = Color.WHITE
                            textSize = 25f
                        }
                        val text = "Archive"
                        val textWidth = textPaint.measureText(text)
                        val textX = right - textWidth - 30f
                        val textY = bottom - 20f
                        c.drawText(text, textX, textY, textPaint)
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
            })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}
