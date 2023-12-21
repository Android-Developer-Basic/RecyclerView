package otus.gpb.recyclerview.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.R
import otus.gpb.recyclerview.data.model.AccountStatus
import otus.gpb.recyclerview.data.model.Chat
import otus.gpb.recyclerview.presentation.recycler.ChatAdapter
import otus.gpb.recyclerview.presentation.recycler.RemoveCallBack

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { ChatAdapter() }
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel.chats.observe(this) {
            adapter.submitList(it)
        }
    }


    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        setupSwipe(recyclerView)
    }

    private fun setupSwipe(recyclerView: RecyclerView) {
        val iconMarginDpRight = 29
        val iconMarginDpTop = 16
        val iconMarginDpBottom = 37

        val background = Paint()
        val icon = getDrawable(R.drawable.arcive_icon)

        background.color = getColor(R.color.backgroundDelete)
        val callback = RemoveCallBack(
            density = resources.displayMetrics.density,
            scaledDensity = resources.displayMetrics.scaledDensity,
            iconMarginDpRight = iconMarginDpRight,
            iconMarginDpTop = iconMarginDpTop,
            swipeAction = {
                val item = adapter.currentList[it.adapterPosition]
                viewModel.deleteChat(item)
            },
            background = background,
            icon = icon
        )
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

}