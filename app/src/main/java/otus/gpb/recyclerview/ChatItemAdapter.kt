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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ChatItemBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class ChatItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun setData(chatItemData: ChatItemData) {

        val view = ChatItemBinding.bind(itemView)

        val context = itemView.context

        view.avatar.setImageDrawable(
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

        with (view.author) {
            text = chatItemData.author
            isVisible = chatItemData.author.isNotEmpty()
        }

        view.titleStatus.setImageDrawable(null)

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
            view.titleStatus.setImageDrawable(ld)
        }

        view.title.text = chatItemData.title

        with (view.message) {
            text = chatItemData.message

            with (layoutParams as ConstraintLayout.LayoutParams){
                bottomToBottom = if (chatItemData.author.isEmpty()) R.id.guideline3 else R.id.guideline4
            }

        }

        view.time.text = SimpleDateFormat("E HH:mm", Locale("RU")).format(Timestamp(chatItemData.time))

        var drwId: Int? = null
        if (ChatItemStatus.CHECK.isSet(chatItemData.status))
            drwId = R.drawable.list_check
        else
            if (ChatItemStatus.READ.isSet(chatItemData.status)) drwId = R.drawable.list_read
        view.timeStatus.setImageDrawable(if (drwId != null) ResourcesCompat.getDrawable(context.resources, drwId, null) else null)

        view.attach.isVisible = ChatItemStatus.ATTACH.isSet(chatItemData.status)
        view.email.isVisible = ChatItemStatus.EMAIL.isSet(chatItemData.status)

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

        parent.children.forEach { child ->
            (parent.adapter as ChatItemAdapter).getItem(parent.getChildAdapterPosition(child))?.let { d ->
                val layerMain = ChatItemBinding.bind(child).layerMain
                c.drawRoundRect(child.left + layerMain.translationX + 5.toPx, child.top + layerMain.translationY + 5.toPx, child.right + layerMain.translationX - 5.toPx, child.bottom + layerMain.translationY - 5.toPx, 20f, 20f, mPaints[d.id % 2])
            }
        }

    }

}

class ChatItemTouchHelperCallback(private val adapter: ChatItemAdapter) : ItemTouchHelper.Callback() {

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

        val view = ChatItemBinding.bind(viewHolder.itemView)

        if ((dX != 0f) || isCurrentlyActive) getDefaultUIUtil().onDraw(c, recyclerView, view.layer2Archive, 0f, 0f, actionState, isCurrentlyActive)

        getDefaultUIUtil().onDraw(c, recyclerView, view.layerMain, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        with(getDefaultUIUtil())
        {
            val view = ChatItemBinding.bind(viewHolder.itemView)
            clearView(view.layer2Archive)
            clearView(view.layerMain)
        }
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
val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()
