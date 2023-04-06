package otus.gpb.recyclerview

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TouchHelper(private val adapter: ChatAdapter, private val context: Context): ItemTouchHelper.Callback() {
    private val paint = Paint().apply {
        color = ContextCompat.getColor(context,R.color.archive_color)
    }
    private val icon: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.archive)
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
        val pos = viewHolder.adapterPosition
        adapter.removeItem(pos)
        adapter.notifyItemRemoved(pos)
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

            val itemView = viewHolder.itemView
            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
            val width = height / 3

            if (dX <= 0) {
                val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                c.drawRect(background, paint)
                val iconDest = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                c.drawBitmap(icon, null, iconDest, paint)

            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}