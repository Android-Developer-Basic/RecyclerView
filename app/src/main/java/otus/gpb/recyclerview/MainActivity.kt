package otus.gpb.recyclerview

import DividerItemDecoration
import SwipeToDeleteCallback
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter
    private val items = mutableListOf<ChatItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ChatAdapter(items)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this)


        recyclerView.addItemDecoration(itemDecoration)



        // Свайп
        val swipeHandler = object : SwipeToDeleteCallback(adapter) {
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val itemView = viewHolder.itemView
                val swipeColor = ContextCompat.getColor(itemView.context, R.color.swipe_color)
                val background = ColorDrawable(swipeColor) // Цвет фона при свайпе

                // Рисуем фон
                background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                background.draw(c)

                // Рисуем иконку удаления
                val deleteIcon = ContextCompat.getDrawable(itemView.context, R.drawable.package_down)
                val iconMargin = (itemView.height - deleteIcon?.intrinsicHeight!!) / 2
                val iconTop = itemView.top + (itemView.height - deleteIcon.intrinsicHeight) / 2 - 20
                val iconBottom = iconTop + deleteIcon.intrinsicHeight

                if (dX < -50) {
                    val iconLeft = itemView.right - iconMargin - deleteIcon.intrinsicWidth
                    val iconRight = itemView.right - iconMargin
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                    deleteIcon.draw(c)

                    // Рисуем текст под иконкой
                    val textPaint = Paint().apply {
                        color = Color.WHITE
                        textSize = 32f // Размер текста
                        typeface = ResourcesCompat.getFont(itemView.context, R.font.roboto_medium)
                    }
                    val text = "Archive" // Текст под иконкой
                    val textWidth = textPaint.measureText(text)
                    val textX = (iconLeft + iconRight - textWidth) / 2
                    val textY = iconBottom + 40F // Расстояние от иконки до текста

                    c.drawText(text, textX, textY, textPaint)
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        items.addAll(generateList())

        adapter.notifyDataSetChanged()

    }

    fun generateList() = run {
        val list = mutableListOf<ChatItem>()
        repeat(50){
            val person = ChatItem(
                id = it,
                name = "Name $it",
                title = "Name title $it",
                message = "This is message $it",
                time = "12:12 AM",
                image = R.drawable.default_avatar
            )
            list.add(person)
        }
        list.toList()
    }
}