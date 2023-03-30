package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import otus.gpb.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val chatAdapter: ChatAdapter by lazy {
        ChatAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter
        }
        chatAdapter.populate(stubItems)

    }

    companion object {
        private var id = 0
        private val stubItems = mutableListOf(
            ChatItem(
                id = ++id,
                userName = "Pizza",
                lastMessage = "Yes, they are necessary",
                date = "11:38 AM",
                isMuted = true
            ),
            ChatItem(
                id = ++id,
                userName = "Elon",
                lastMessage = "I love /r/ Reddit!",
                date = "12:44 AM",
                isMuted = true
            ),
            ChatItem(
                id = ++id,
                userName = "Pavel",
                lastMessage = "How are you?",
                date = "Fri",
                isMuted = true,
                isVerified = true,
                unreadMessageCount = 1111
            ),
            ChatItem(
                id = ++id,
                userName = "User X",
                lastMessage = "deelete it now",
                date = "Thu",
                isMuted = true,
                isLastMessageMine = true,
                isMessageDelivered = true,
                isMessageRead = true
            ),
            ChatItem(
                id = ++id,
                userName = "Telegram support",
                lastMessage = "Yes, it happened.",
                date = "Thu",
                isVerified = true,
                unreadMessageCount = 1
            ),
            ChatItem(
                id = ++id,
                userName = "Karinaa",
                lastMessage = "okay",
                date = "Wed",
                isLastMessageMine = true,
                isMessageDelivered = true
            ),
            ChatItem(
                id = ++id,
                userName = "Marilyn",
                lastMessage = "Will it ever happen",
                date = "May 02",
                isScam = true,
                isLastMessageMine = true,
                isMessageDelivered = true,
                isMessageRead = true
            ),
            ChatItem(
                id = ++id,
                userName = "all icons check",
                lastMessage = "Checked",
                date = "Tue",
                isScam = true,
                isVerified = true,
                isMuted = true,
                unreadMessageCount = 3,
                isLastMessageMine = true,
                isMessageDelivered = true,
                isMessageRead = true
            )
        )
    }
}