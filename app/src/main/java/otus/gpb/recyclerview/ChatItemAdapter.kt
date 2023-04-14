package otus.gpb.recyclerview

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class ChatItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun setData(chatItemData: ChatItemData) {

        val context = itemView.context

        itemView.findViewById<ImageView>(R.id.avatar).setImageDrawable(
            if (chatItemData.avatar.compareTo(0u) == 0)
                null
            else {
                ResourcesCompat.getDrawable(
                    context.resources,
                    context.resources.getIdentifier(
                        "widget_avatar_${chatItemData.avatar}",
                        "drawable",
                        context.packageName
                    ),
                    null
                )
            }
        )

        itemView.findViewById<TextView>(R.id.author).apply {
            text = chatItemData.author
            isVisible = chatItemData.author.isNotEmpty()
        }

        val ts = itemView.findViewById<ImageView>(R.id.title_status)
        ts.setImageDrawable(null)

        itemView.findViewById<TextView>(R.id.title).apply {
            text = chatItemData.title

            val img = MutableList<Drawable?>(0) { null }
            if (ChatItemStatus.SCAM.isSet(chatItemData.status)) img.add(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.list_scam,
                    null
                )
            )
            if (ChatItemStatus.FAKE.isSet(chatItemData.status)) img.add(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.list_fake,
                    null
                )
            )
            if (ChatItemStatus.VERIFY.isSet(chatItemData.status)) img.add(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.list_verified_profile,
                    null
                )
            )
            if (ChatItemStatus.MUTE.isSet(chatItemData.status)) img.add(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.list_mute,
                    null
                )
            )
            if (ChatItemStatus.SECRET.isSet(chatItemData.status)) img.add(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.list_secret,
                    null
                )
            )

            if (img.isNotEmpty()) {
                var s = 0
                val ld = LayerDrawable(img.toTypedArray())
                for (i in 0 until ld.numberOfLayers) {
                    val w = ld.getDrawable(i).intrinsicWidth + 10
                    ld.setLayerInset(i, s, 0, s + w, 0)
                    ld.setLayerGravity(i, Gravity.START or Gravity.CENTER_VERTICAL)
                    s += w
                }
                ts.setImageDrawable(ld)
            }
        }

        itemView.findViewById<TextView>(R.id.message).apply {
            text = chatItemData.message

            with (layoutParams as ConstraintLayout.LayoutParams){
                bottomToBottom = if (chatItemData.author.isEmpty()) R.id.guideline3 else R.id.guideline4
            }

        }

        itemView.findViewById<TextView>(R.id.time).apply {
            text = SimpleDateFormat("E HH:mm", Locale("RU")).format(Timestamp(chatItemData.time))
        }

        itemView.findViewById<ImageView>(R.id.time_status).apply {

            var drwId: Int? = null
            if (ChatItemStatus.CHECK.isSet(chatItemData.status))
                drwId = R.drawable.list_check
            else
                if (ChatItemStatus.READ.isSet(chatItemData.status)) drwId = R.drawable.list_read

            setImageDrawable(if (drwId != null) ResourcesCompat.getDrawable(context.resources, drwId, null) else null)

        }

        itemView.findViewById<ImageView>(R.id.attach).isVisible = ChatItemStatus.ATTACH.isSet(chatItemData.status)
        itemView.findViewById<ImageView>(R.id.email).isVisible = ChatItemStatus.EMAIL.isSet(chatItemData.status)

    }

}

class ChatItemAdapter: RecyclerView.Adapter<ChatItemViewHolder>() {

    private val mChatData = mutableListOf<ChatItemData>()

    init {
        clearData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ChatItemViewHolder(view)
    }

    override fun getItemCount(): Int = mChatData.size

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.setData(mChatData[position])
    }

    fun clearData() {
        mChatData.clear()
        notifyDataSetChanged()
    }

    fun setItem(newData: ChatItemData, notify: Boolean = true, position: Int = -1) {
        if (position == -1)
        {
            mChatData.add(newData)
            if (notify) notifyItemInserted(mChatData.size - 1)
        }
        else
        {
            mChatData[position] = newData
            if (notify) notifyItemChanged(position)
        }
    }

    fun removeItem(position: Int) {
        mChatData.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItem(position: Int): ChatItemData? = if (position in 0 until mChatData.size) mChatData[position] else null

    fun maxID() = mChatData.maxOf { it.id }
}

class ChatItemDecoration : RecyclerView.ItemDecoration() {

    private val mPaints = arrayOf(Paint(), Paint())

    init {
        mPaints[0].color = Color.rgb(0, 200, 100)
        mPaints[0].strokeWidth = 5f
        mPaints[0].style = Paint.Style.STROKE

        mPaints[1].color = Color.rgb(200, 200, 0)
        mPaints[1].strokeWidth = 5f
        mPaints[1].style = Paint.Style.STROKE
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        parent.children.forEach {
            (parent.adapter as ChatItemAdapter).getItem(parent.getChildAdapterPosition(it))?.let { d ->
                c.drawRoundRect(it.left + it.translationX, it.top.toFloat(), it.right + it.translationX, it.bottom.toFloat(), 20f, 20f, mPaints[d.id % 2])
            }
        }

    }

}

class ChatItemTouchHelperCallback(private val adapter: ChatItemAdapter) : ItemTouchHelper.Callback() {

    private val mPaint = Paint()
    private val mClear = Paint()

    init {
        mPaint.color = Color.parseColor("#4B91CA")
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 1f

        mClear.color = Color.WHITE
        mClear.style = Paint.Style.FILL
        mClear.strokeWidth = 1f
    }

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
        adapter.removeItem(viewHolder.adapterPosition)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        //return super.getSwipeThreshold(viewHolder)
        return .3f
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
        //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)


        if ((dX == 0f) && !isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, false)
            return
        }

        val itemView = viewHolder.itemView
        val itemHeight = itemView.height

        if ((itemView.right + dX > 0) && (dX < 0)) {

            c.drawRoundRect(
                itemView.right + dX - 20,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat(),
                20f,
                20f,
                mPaint
            )

            c.drawRoundRect(
                itemView.right + dX - 20,
                itemView.top.toFloat(),
                itemView.right.toFloat() + dX,
                itemView.bottom.toFloat(),
                20f,
                20f,
                mClear
            )
        }

        ResourcesCompat.getDrawable(
            itemView.context.resources,
            R.drawable.chats_archive,
            null
        )?.apply {
            val archiveIconMargin: Int = (itemHeight - intrinsicHeight) / 2
            val archiveIconTop: Int = itemView.top + (itemHeight - intrinsicHeight) / 2

            setBounds(
                itemView.right - archiveIconMargin - intrinsicWidth,
                archiveIconTop,
                itemView.right - archiveIconMargin,
                archiveIconTop + intrinsicHeight
            )

            draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}

class ChatScrollListener(private val layoutManager: LinearLayoutManager, private val adapter: ChatItemAdapter): RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if ((layoutManager.findFirstVisibleItemPosition() + layoutManager.childCount >= layoutManager.itemCount) && (layoutManager.itemCount < 50))
            Handler(Looper.getMainLooper()).post {
                val startPosition = adapter.itemCount
                var count = 10
                var id = adapter.maxID() + 1
                while (count > 0)
                {
                    adapter.setItem(createChatItemData(id, false), false)
                    count--
                    id++
                }
                adapter.notifyItemRangeInserted(startPosition, 10)
            }
    }
}

val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).toInt()
