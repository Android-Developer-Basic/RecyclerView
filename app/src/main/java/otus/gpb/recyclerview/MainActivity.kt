package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.ChatAdapter
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        ChatAdapter(ChatCreator.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        setupView()
    }

    private fun setupView() {
        binding.recyclerView.adapter = adapter

        val decorator = DividerItemDecoration(this, VERTICAL).apply {
            val drawableDecorator = ResourcesCompat.getDrawable(resources,
                R.drawable.chat_decorator, theme) ?: return
            setDrawable(drawableDecorator)
        }
        // Я не знаю, как сделать декоратор на часть делегата =(
        binding.recyclerView.addItemDecoration(decorator)

        // Пагинация как в лекции
        binding.recyclerView.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            val lastVisibleItemPos = ((view as RecyclerView).layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (lastVisibleItemPos == adapter.itemCount - 1)
                adapter.addChats(ChatCreator.create())
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == LEFT) {
                    // Я не знаю как здесь показать плашку про удаление чата, как в фигме
                    adapter.removeChat(viewHolder.adapterPosition)
                }
            }

        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}