package otus.gpb.recyclerview

import DividerItemDecoration
import SwipeToDeleteCallback
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ChatAdapter()

        val itemDecoration = DividerItemDecoration(this)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.addItemDecoration(itemDecoration)



        // Свайп
        val swipeHandler = object : SwipeToDeleteCallback(adapter) {
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                // Реализация анимации свайпа
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        adapter.setItems(generateList())

        recyclerView.adapter = adapter


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