package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.divider.MaterialDividerItemDecoration.VERTICAL
import otus.gpb.recyclerview.databinding.ActivityMainBinding
import otus.gpb.recyclerview.service.ChatService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        ChatAdapter(ChatService.getChatList())
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
        val decorator = MaterialDividerItemDecoration(this, VERTICAL).apply {
            dividerColor = resources.getColor(R.color.dividerColor, theme)
            dividerInsetStart = 240
            isLastItemDecorated = false
        }

        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(decorator)
        }
    }
}
