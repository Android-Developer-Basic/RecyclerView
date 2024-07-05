package otus.gpb.recyclerview

import DividerItemDecoration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ChatAdapter()

        val itemDecoration = DividerItemDecoration(this)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.addItemDecoration(itemDecoration)

        recyclerView.adapter = adapter

        adapter.setItems(generateList())


    }

    fun generateList() = run {
        val list = mutableListOf<ChatItem>()
        repeat(50){
            val person = ChatItem(
                id = it,
                name = "Name $it",
                title = "Name title $it",
                message = "This is message $it",
                time = "12:12 AM",
                image = R.drawable.default_avatar
            )
            list.add(person)
        }
        list.toList()
    }
}