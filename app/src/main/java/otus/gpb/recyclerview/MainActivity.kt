package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import otus.gpb.recyclerview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val chatAdapter = ChatAdapter()
    private val chatSet = mutableSetOf<Int>()
    private val list = mutableListOf<Int>()
    private var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        val names = this.resources.getStringArray(R.array.names)
        val messages = this.resources.getStringArray(R.array.messages)
        binding.recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = chatAdapter
            (adapter as ChatAdapter).singleLoadingClickListener {
                loadSingleItem(adapter as ChatAdapter, names, messages)

            }
            addItemDecoration(DividerDecorator(this@MainActivity))
            addOnScrollListener(ScrollListener(layoutManager).apply {
                loadListener {
                    if(total < names.size) {
                        loadItemsToRV(adapter as ChatAdapter, names, messages)
                        Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }

        val touchHelper = ItemTouchHelper(TouchHelper(binding.recyclerView.adapter as ChatAdapter, this))
        touchHelper.attachToRecyclerView(binding.recyclerView)
        fillChatSet(names.size)
        loadItemsToRV(binding.recyclerView.adapter as ChatAdapter, names, messages)

    }

    private fun fillChatSet(size: Int){
        while (chatSet.size < size) {
            chatSet.add((Math.random()*size).toInt())
        }
        chatSet.map { list.add(it) }
    }


    private fun loadItemsToRV(adapter: ChatAdapter,names: Array<String>, messages: Array<String>){
        var count = total
        val size = list.size - total
        val limit = if(size - total < 0) size else 10
        while (count < total+limit){
            val chatData = ChatData(names, messages,list[count])
            adapter.addChat(chatData.createChat())
            count++
        }
        total = count

    }

    private fun loadSingleItem(adapter: ChatAdapter, names: Array<String>, messages: Array<String>){
        if(total < list.size){
            Toast.makeText(this@MainActivity, "Single item loading", Toast.LENGTH_SHORT).show()
            val chatData = ChatData(names, messages, list[total])
            adapter.addChat(chatData.createChat())
            total++
        }
    }

}