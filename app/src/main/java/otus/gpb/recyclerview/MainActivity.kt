package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val listView : RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val adapter : ChatAdapter by lazy { ChatAdapter() }

    private val imageChatList = listOf(
        R.drawable.image_pizza,
        R.drawable.image_elon,
        R.drawable.image_pasha,
        R.drawable.image_telegram,
        R.drawable.image_karina,
        R.drawable.image_marilyn
    )
    private val titleList = listOf("Pizza","Elon","Pasha","Telegram","Karina","Marilyn")
    private val verifiedList = listOf(0,0,R.drawable.verified_icon,R.drawable.verified_icon,0,0)
    private val muteList = listOf(R.drawable.mute_icon,R.drawable.mute_icon,R.drawable.mute_icon,0,0,0)
    private val scamList = listOf(0,0,0,0,0,R.drawable.scam_icon)
    private val messageAuthorList = listOf("jija","","","Support","","")
    private val previewList = listOf(R.drawable.icon_preview,0,0,0,0,0)
    private val messageTextList = listOf("Yes, they are necessary", "I love /r/Reddit!",
        "How are u?","Yes it happened.","Okay","Will it ever happen")
    private val checkList = listOf(0,0,0,0,0,R.drawable.check_icon)
    private val readList = listOf(0,0,0,0,R.drawable.read_icon,0)
    private val timeList = listOf("11:38 AM","12:44 AM","Fri","Thu","Wed","May 02")
    private val counterList = listOf(0,0,0,R.drawable.counter_icon,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val dividerDrawable = AppCompatResources.getDrawable(this,R.drawable.divider)
//        val defDivider = DividerItemDecoration(this,DividerItemDecoration.VERTICAL).apply {
//            dividerDrawable?.let { setDrawable(it) }
//        }
//        listView.addItemDecoration(defDivider)
        listView.addItemDecoration(CustomDecorator())

        ItemTouchHelper(ItemTouchCallback(adapter)).attachToRecyclerView(listView)

        listView.adapter = adapter
        adapter.setChat(fillList())
    }

    private fun fillList():List<Chat> {
        val mutableList = mutableListOf<Chat>()
        var id = 0
        for(i in 0..20){
            if (id >5){id=0}
            val record = Chat(
                avatar = imageChatList[id],
                title = titleList[id],
                verify = verifiedList[id],
                mute = muteList[id],
                scam = scamList[id],
                messageAuthor = messageAuthorList[id],
                preview = previewList[id],
                message = messageTextList[id],
                check = checkList[id],
                read = readList[id],
                time = timeList[id],
                counter = counterList[id]
            )
            mutableList.add(record)
            id++

        }
        return mutableList
    }
}