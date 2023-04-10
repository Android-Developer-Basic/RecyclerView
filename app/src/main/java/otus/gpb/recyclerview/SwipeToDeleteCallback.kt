package otus.gpb.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteCallback(
    private val adapter: ChatAdapter
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private val backgroundColor = Color.parseColor("#66A9E0")
    private var icon: Drawable? = null
    private val textPaint = Paint().apply {
        color = Color.WHITE
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

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
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        if (icon == null) {
            icon = ContextCompat.getDrawable(recyclerView.context, R.drawable.archive_icon)
        }
        val itemView = viewHolder.itemView
        c.save()
        c.clipRect(
            itemView.right + dX,
            itemView.top + dY,
            itemView.right * 2 + dX,
            itemView.bottom + dY
        );
        c.translate(itemView.right + dX, itemView.top + dY);
        c.drawColor(backgroundColor)
        val marginTop = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            16f,
            recyclerView.context.resources.displayMetrics
        )
        val marginRight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            29f,
            recyclerView.context.resources.displayMetrics
        )
        val iconSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            27f,
            recyclerView.context.resources.displayMetrics
        )
        icon?.setBounds(
            (-dX - marginRight - iconSize).toInt(),
            marginTop.toInt(),
            (-dX - marginRight).toInt(),
            (marginTop + iconSize).toInt()
        )
        icon?.draw(c)
        textPaint.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            13f,
            recyclerView.context.resources.displayMetrics
        )
        val text = "Archive"
        val textWidth = textPaint.measureText(text)
        val textY = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            49f,
            recyclerView.context.resources.displayMetrics
        )
        c.drawText(
            text,
            -dX - marginRight - iconSize - (textWidth - iconSize) / 2,
            textY + textPaint.textSize / 2,
            textPaint
        )
        c.restore()
    }
}