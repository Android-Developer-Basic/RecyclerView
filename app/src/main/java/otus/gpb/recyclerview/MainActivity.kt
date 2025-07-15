package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import otus.gpb.recyclerview.data.ChatItem
import otus.gpb.recyclerview.data.groupChats
import otus.gpb.recyclerview.data.userChats
import otus.gpb.recyclerview.databinding.ActivityMainBinding
import otus.gpb.recyclerview.ui.ChatAdapter
import otus.gpb.recyclerview.ui.CustomDecorator
import otus.gpb.recyclerview.ui.SwipeToDeleteCallback

class MainActivity : AppCompatActivity() {

    private var chatItems: MutableList<ChatItem> =
        (groupChats.map { ChatItem.Group(it) } +
                userChats.map { ChatItem.User(it) }).toMutableList()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.addItemDecoration(CustomDecorator(this).apply {
            setColor(R.color.color_chat_divider_light)
            setOffset(R.integer.dividerOffset)
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ChatAdapter() { id ->
            println(id)
        }

        binding.recyclerView.adapter = adapter

        adapter.submitList(chatItems.toList())

        val itemTouchHelper = ItemTouchHelper(
            SwipeToDeleteCallback ({ position -> removeItem(position)}, this)
        )
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

    }

    private fun removeItem(position: Int) {
        chatItems.removeAt(position)
        adapter.submitList(chatItems.toList())
    }

}