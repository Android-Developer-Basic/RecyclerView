package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { ChatAdapter{
        onClickListener(it)
    } }
    private val presenter by lazy { Presenter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        recyclerView.adapter = adapter
        adapter.setChats(presenter.getChats())
    }

    fun onClickListener(position: Int){
        adapter.deleteChat(position)
    }
}