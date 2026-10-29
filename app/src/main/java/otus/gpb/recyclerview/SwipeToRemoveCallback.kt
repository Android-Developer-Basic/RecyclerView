package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.withClip

/**
 * Свайп влево: под ячейкой открывается красная плашка «Remove», при полном свайпе ячейка удаляется
 */
class SwipeToRemoveCallback(
    context: Context,
    private val onSwiped: (position: Int) -> Unit,
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val background =
        ContextCompat.getColor(context, R.color.chat_swipe_background).toDrawable()
    private val icon = requireNotNull(ContextCompat.getDrawable(context, R.drawable.ic_delete))
    private val label = context.getString(R.string.remove)
    private val iconSize = context.dp(24f).toInt()
    private val iconTop = context.dp(18f).toInt()
    private val labelBaseline = context.dp(62f)
    private val contentEndOffset = context.dp(43f)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14f, context.resources.displayMetrics)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            onSwiped(position)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean,
    ) {
        val itemView = viewHolder.itemView
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX < 0) {
            val left = itemView.right + dX.toInt()
            background.setBounds(left, itemView.top, itemView.right, itemView.bottom)
            background.draw(c)

            // Иконка и подпись стоят у правого края и проявляются по мере открытия плашки
            c.withClip(left, itemView.top, itemView.right, itemView.bottom) {
                val centerX = itemView.right - contentEndOffset
                val iconLeft = (centerX - iconSize / 2f).toInt()
                icon.setBounds(
                    iconLeft,
                    itemView.top + iconTop,
                    iconLeft + iconSize,
                    itemView.top + iconTop + iconSize
                )
                icon.draw(this)
                drawText(label, centerX, itemView.top + labelBaseline, textPaint)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun Context.dp(value: Float): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}
