package otus.gpb.recyclerview

import android.app.Application

class RecyclerViewApplication: Application() {
  fun getChatItemsRepository(): ChatItemsRepository {
    return ChatItemsRepositoryImpl.getInstance()
  }
}