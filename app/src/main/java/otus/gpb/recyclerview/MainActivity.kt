package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL
import otus.gpb.recyclerview.databinding.ActivityMainBinding
import otus.gpb.recyclerview.service.ChatService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val chatAdapter by lazy {
        ChatAdapter(ChatService().getChatList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    private fun setupView() {
        val decorator = MaterialDividerItemDecoration(this, VERTICAL).apply {
            dividerColor = resources.getColor(R.color.dividerColor, theme)
            dividerInsetStart =
                this@MainActivity.resources.getDimensionPixelSize(R.dimen.divider_padding_start)
            isLastItemDecorated = false
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

            private val background =
                ColorDrawable(resources.getColor(R.color.swipedBackground, theme))
            private val icon = ResourcesCompat.getDrawable(resources, R.drawable.archive, theme)
            private val iconWidth = icon?.intrinsicWidth
            private val iconHeight = icon?.intrinsicHeight
            private val iconMargin = resources.getDimensionPixelSize(R.dimen.archive_icon_margin)

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

                with(background) {
                    setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    draw(c)
                }

                val iconLeft = itemView.right - iconWidth!! - iconMargin
                val iconRight = itemView.right - iconMargin
                val verticalMargin = (itemView.height - iconHeight!!) / 2
                val iconTop = itemView.top + verticalMargin
                val iconBottom = itemView.bottom - verticalMargin

                icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                icon?.draw(c)

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
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                chatAdapter.removeItem(viewHolder.adapterPosition)
            }
        })

        with(binding) {
            recyclerView.apply {
                adapter = chatAdapter
                addItemDecoration(decorator)

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val totalItems = recyclerView.layoutManager!!.itemCount
                        val lastVisibleItem =
                            (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (lastVisibleItem == totalItems - 1) {
                            chatAdapter.addItems()
                        }
                    }
                })
            }

            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }
}
