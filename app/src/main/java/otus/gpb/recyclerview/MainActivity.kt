package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import otus.gpb.recyclerview.model.AccountStatus
import otus.gpb.recyclerview.model.Chat
import otus.gpb.recyclerview.recycler.ChatAdapter

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { ChatAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        adapter.chatList = randomData(20)
    }


    fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
    }

    fun randomData(countData: Int): List<Chat> {
        val list = emptyList<Chat>().toMutableList()
        val chatModel = Chat(
            0,
            "fan avengers",
            "sfdds",
            R.drawable.mstiteli,
            false,
            "Alex: последний фильм был хорош",
            "11:50 AM",
            true,
            0,
            AccountStatus.Group
        )
        for (i in 0 until countData) {
            list.add(chatModel.copy(id = i))
        }
        return list
    }

}