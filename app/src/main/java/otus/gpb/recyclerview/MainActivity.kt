package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.ChatRepository.Companion.listChat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val chatAdapter = ChatAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chatAdapter
        recyclerView.addItemDecoration(RecyclerViewItemDecoration(this, R.drawable.divider))

        chatAdapter.submitList(listChat)

        val button: Button = findViewById(R.id.add_button)
        button.setOnClickListener {
            listChat.add(
                Chat(
                    55,
                    "Chat55",
                    "Message55",
                    "Writer55",
                    "",
                    false,
                    false,
                    false,
                    false,
                    true,
                    "11:55",
                    true,
                )
            )
            chatAdapter.notifyDataSetChanged()
        }

        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    chatAdapter.removeItem(position)
                    chatAdapter.notifyDataSetChanged()
                }
            })

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

}