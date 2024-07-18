package otus.gpb.recyclerview

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(
    private val chatAdapter: ChatAdapter,
    private val resources: Resources
) : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(
                ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT
            )
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            val background = ColorDrawable(Color.parseColor("#66A9E0"))

            background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
            background.draw(canvas)

            val scaledDensity = resources.displayMetrics.scaledDensity
            val density = resources.displayMetrics.density

            fun spToPx(sp: Float): Float = sp * scaledDensity
            fun dpToPx(dp:Int): Int = (density * dp + 0.5f).toInt()

            val archiveIcon = ContextCompat.getDrawable(itemView.context, R.drawable.archive)
            val iconHeight = archiveIcon?.intrinsicHeight!!

            val iconTop = itemView.top + dpToPx(16)
            val iconBottom = iconTop + iconHeight
            val iconRight = itemView.right - dpToPx(29)
            val iconLeft = iconRight - archiveIcon.intrinsicWidth

            archiveIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            archiveIcon.draw(canvas)

            val textPaint = Paint().apply {
                color = Color.WHITE
                textSize = spToPx(13F)
            }
            val text = "Archive"
            val textWidth = textPaint.measureText(text)
            val textX = itemView.right - dpToPx(20).toFloat() - textWidth
            val textY = itemView.bottom - dpToPx(16).toFloat()

            canvas.drawText(text, textX, textY, textPaint)
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            chatAdapter.removeItem(position)
        }
}
