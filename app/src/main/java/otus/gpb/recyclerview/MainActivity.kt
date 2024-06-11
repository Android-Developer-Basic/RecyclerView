package otus.gpb.recyclerview

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.Adapters.ChatAdapter
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribe()
    }

    fun subscribe() {
        viewModel.chats.observe(this) { chats ->
            // Update UI with the new count value
            adapter.addList(chats)
        }

        adapter = ChatAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.loadData()
    }
}