package otus.prokofev.recyclerview

import android.graphics.*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        initAdapter()
        setupView()
    }

    private fun initAdapter() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                private val clearPaint =
                    Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

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
                        onRemoveItemClicked(position)
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
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        var paint = Paint()

                        // Drawing for Swipe left
                        val itemView = viewHolder.itemView
                        val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                        val isCanceled = dX == 0f && !isCurrentlyActive

                        if (isCanceled) {
                            clearCanvas(
                                c,
                                itemView.right + dX,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            super.onChildDraw(
                                c,
                                recyclerView,
                                viewHolder,
                                dX,
                                dY,
                                actionState,
                                isCurrentlyActive
                            )
                            return
                        }

                        if (dX < 0) {
                            val archiveIcon = ContextCompat.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_baseline_archive_24
                            )
                            archiveIcon?.setTint(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.white
                                )
                            )
                            val intrinsicWidth = archiveIcon?.intrinsicWidth
                            val intrinsicHeight = archiveIcon?.intrinsicHeight

                            //Drawing for Swipe Left
                            paint.color = getColor(R.color.little_boy_blue)
                            val background = RectF(
                                itemView.right.toFloat() + dX,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            c.drawRect(background, paint)

                            // Calculate position of archive icon
                            val deleteIconTop = itemView.top + (height - intrinsicHeight!!) / 2
                            val deleteIconMargin = (height - intrinsicHeight) / 2
                            val deleteIconLeft =
                                itemView.right - deleteIconMargin - intrinsicWidth!!
                            val deleteIconRight = itemView.right - deleteIconMargin
                            val deleteIconBottom = deleteIconTop + intrinsicHeight

                            // Draw the archive icon
                            archiveIcon.setBounds(
                                deleteIconLeft.toInt(),
                                deleteIconTop.toInt(),
                                deleteIconRight.toInt(),
                                deleteIconBottom.toInt()
                            )

                            archiveIcon.draw(c)
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

                private fun clearCanvas(
                    c: Canvas?,
                    left: Float,
                    top: Float,
                    right: Float,
                    bottom: Float
                ) {
                    c?.drawRect(left, top, right, bottom, clearPaint)
                }
            }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupView() {
        val chats = (application as Application).chats
        val additionalChats = (application as Application).indefiniteChats

        recyclerView.adapter = ChatAdapter(chats, object : ChatAdapter.ChatClickListener {
            override fun onChatClick(chat: Chat, position: Int) {
                Toast.makeText(this@MainActivity, "Item was clicked!", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.setOnScrollChangeListener { _, p1, p2, p3, p4 ->
            if (p4 < 0) {
                (recyclerView.adapter as ChatAdapter).addItems(additionalChats)
            }
        }


        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decorator)
    }

    private fun onRemoveItemClicked(position: Int) {
        (recyclerView.adapter as ChatAdapter).removeItem(position)
    }
}