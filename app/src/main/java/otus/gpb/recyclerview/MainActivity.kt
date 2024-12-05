package otus.gpb.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)

        val b = ActivityMainBinding.inflate(layoutInflater)

        setContentView(b.root)

        b.chatContainer.apply rv@ {
            val ad = ChatItemAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = ad
            addItemDecoration(ChatItemDecoration())

            ItemTouchHelper(ChatItemTouchHelperCallback(ad)).apply {
                attachToRecyclerView(this@rv)
            }

            addOnScrollListener(ChatScrollListener(layoutManager as LinearLayoutManager, ad))

            for (i in 0..19) ad.setItem(createChatItemData(i, false), false)
            ad.notifyItemRangeInserted(0, 19)
        }

    }

}
