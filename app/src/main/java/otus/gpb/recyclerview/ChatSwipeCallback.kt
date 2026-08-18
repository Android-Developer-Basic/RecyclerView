package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ChatSwipeCallback(
    context: Context,
    private val onDismissed: (chatId: Long) -> Unit,
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private val density = context.resources.displayMetrics.density
    private val archiveIcon: Drawable = requireNotNull(
        ContextCompat.getDrawable(context, R.drawable.ic_archive),
    ).mutate()
    private val archiveLabel = context.getString(R.string.archive)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.swipe_archive)
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = context.resources.getDimension(R.dimen.swipe_label_size)
        textAlign = Paint.Align.CENTER
        typeface = android.graphics.Typeface.create("sans-serif-medium", android.graphics.Typeface.NORMAL)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (viewHolder.itemId != RecyclerView.NO_ID) onDismissed(viewHolder.itemId)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder) = 0.7f

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean,
    ) {
        val item = viewHolder.itemView
        if (dX < 0f) {
            canvas.drawRect(
                item.right + dX,
                item.top.toFloat(),
                item.right.toFloat(),
                item.bottom.toFloat(),
                backgroundPaint,
            )

            val centerX = item.right - 41f * density
            val centerY = (item.top + item.bottom) / 2f
            val iconSize = (30f * density).toInt()
            val iconCenterY = centerY - 9f * density
            archiveIcon.setBounds(
                (centerX - iconSize / 2f).toInt(),
                (iconCenterY - iconSize / 2f).toInt(),
                (centerX + iconSize / 2f).toInt(),
                (iconCenterY + iconSize / 2f).toInt(),
            )
            archiveIcon.draw(canvas)
            canvas.drawText(archiveLabel, centerX, centerY + 28f * density, textPaint)
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}