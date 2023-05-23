package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class MyTouchHelperCallBack(
    private val context: Context
) : ItemTouchHelper.Callback() {

    private val icon = AppCompatResources.getDrawable(context, R.drawable.ic_archive_32)
    private val iconIntrinsicWidth = icon?.let {icon.intrinsicWidth}!!
    private val iconIntrinsicHeight = icon?.let {icon.intrinsicHeight}!!
    private val iconLeftMargin = 42

    private val background = ColorDrawable()
    private val backgroundColor = context.getColor(R.color.blue_400)

    private val clearPaint = Paint()
    private val paint = Paint()


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isItemViewSwipeEnabled(): Boolean = true
    override fun isLongPressDragEnabled(): Boolean = true

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        if (viewHolder.adapterPosition == RecyclerView.NO_POSITION)
            return


//TODO вопросы вопросы отус ресайкл.txt
//        3. через ДиффУтил


        val itemView = viewHolder.itemView
        val itemViewHeight = itemView.height

        val isCanceled: Boolean = (dX == 0F) && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(
                c,
                itemView.right + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        background.color = backgroundColor
        background.setBounds(
            dX.toInt() + itemView.right,
            itemView.top - MyDecoration.TOP_OFFSET.dp2px(context),
            itemView.right,
            itemView.bottom + MyDecoration.BOTTOM_OFFSET.dp2px(context))
        background.draw(c)

        val iconLeft = itemView.right - iconIntrinsicWidth - iconLeftMargin
        val iconRight = itemView.right - iconLeftMargin

        val verticalMargin = (itemViewHeight - iconIntrinsicHeight) / 2

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

        val textLeft = itemView.right - iconIntrinsicWidth
        val textTop = iconTop + iconIntrinsicHeight + verticalMargin /2

        paint.color = Color.WHITE
        paint.textSize = TEXT_SIZE.sp(context)
        paint.textAlign = Paint.Align.CENTER
        c.drawText(LABEL_TEXT, textLeft.toFloat(), textTop.toFloat(), paint)


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }


    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
//        Returns the fraction that the user should move the View to be considered as swiped.
//        return super.getSwipeThreshold(viewHolder)
        return 0.7F
    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        c.drawRect(left, top, right, bottom, clearPaint)
    }

    private fun Float.sp(context: Context) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context.resources.displayMetrics
    )
    private fun Int.dp2px(context: Context): Int =
        (this *context.resources.displayMetrics.density).toInt()

    companion object {
        private const val LABEL_TEXT = "Archive"
        private const val TEXT_SIZE = 12F
    }
}