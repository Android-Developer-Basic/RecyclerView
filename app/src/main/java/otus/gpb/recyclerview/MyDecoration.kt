package otus.gpb.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyDecoration(
    private  val context: Context
) : RecyclerView.ItemDecoration() {

    private val rect = Rect()
    private val paint = Paint().apply {
        color = context.getColor(R.color.grey_100)
        strokeWidth = STROKE_WIDTH
    }

    private lateinit var avatarView: ShapeableImageView
    private lateinit var userNameView: TextView

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            top = TOP_OFFSET.dp2px(context)
            bottom = BOTTOM_OFFSET.dp2px(context)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        // TODO each time to find views???
        avatarView = parent.findViewById(R.id.user_avatar)
        userNameView = parent.findViewById(R.id.user_name)

        val left = avatarView.width +
                parent.paddingLeft +
                avatarView.marginStart +
                avatarView.marginEnd +
                userNameView.marginStart
        val right = parent.width

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, rect)
            c.drawLine(
                left.toFloat(),
                rect.top.toFloat(),
                right.toFloat(),
                rect.top.toFloat(),
                paint
            )
        }
    }

    private fun Int.dp2px(context: Context): Int =
        (this *context.resources.displayMetrics.density).toInt()

    companion object {
        const val TOP_OFFSET = 2
        const val BOTTOM_OFFSET = 2
        private const val STROKE_WIDTH = 2F
    }
}