package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val chatAdapter: ChatAdapter by lazy {
        ChatAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView)?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter

            val divider = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)

            ResourcesCompat.getDrawable(resources, R.drawable.black_line_divider, theme)?.let {
                divider.setDrawable(it)
            }
            addItemDecoration(divider)
        }

        chatAdapter.submitData(ChatApp().getItems())
    }
}