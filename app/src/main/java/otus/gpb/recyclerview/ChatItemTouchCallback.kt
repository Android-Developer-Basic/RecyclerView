package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ChatItemTouchCallback(private val onRemove: (Int) -> Unit) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int =
        makeMovementFlags(0, ItemTouchHelper.LEFT)

    override fun onMove(
        p0: RecyclerView,
        p1: RecyclerView.ViewHolder,
        p2: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) =
        onRemove(viewHolder.absoluteAdapterPosition)


    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float = 0.5F

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        (viewHolder as? ChatItemViewHolder)?.apply {
            val rect = Rect(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            val widthSpec = View.MeasureSpec.makeMeasureSpec(rect.width(), View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(rect.height(), View.MeasureSpec.EXACTLY)
            backgroundView.measure(widthSpec, heightSpec)
            backgroundView.layout(0, 0, rect.width(), rect.height())
            c.save()
            c.translate(rect.left.toFloat(), rect.top.toFloat())
            backgroundView.draw(c)
            c.restore()
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}