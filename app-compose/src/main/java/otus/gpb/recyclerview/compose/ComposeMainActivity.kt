package otus.gpb.recyclerview.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity

/**
 * Тот же список чатов, что и во View-версии из модуля :app, но на Compose
 */
class ComposeMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ChatListScreen() }
    }
}
