package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var ChatTlgList:List<ChatTlgItem> = mutableListOf()
    private lateinit var chatTlgAdapter:ChatTlgAdapter
    private lateinit var dividerItemDecoration : DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ChatTlgList = ChatTlgService( true ).getTlgChat()
        chatTlgAdapter = ChatTlgAdapter( )

        val manager = LinearLayoutManager(this) // LayoutManager
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.adapter = chatTlgAdapter
        rv.layoutManager = manager
        dividerItemDecoration = DividerItemDecoration(
            rv.context,
            (rv.layoutManager as LinearLayoutManager).orientation
        )
        rv.addItemDecoration(dividerItemDecoration)

        chatTlgAdapter.setList( ChatTlgList )

        var callback = TlgItemTouchHelper( chatTlgAdapter )
        var itemTouchHelper = ItemTouchHelper( callback )
        itemTouchHelper.attachToRecyclerView( rv )
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    var chatAddList = ChatTlgService( false )
                    ChatTlgList  = ChatTlgList + chatAddList.getTlgChat()
                    chatTlgAdapter.setList( ChatTlgList )
                }
            }
        })
    }
}