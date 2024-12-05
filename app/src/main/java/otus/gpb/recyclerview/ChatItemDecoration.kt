package otus.gpb.recyclerview

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration

class ChatItemDecoration(context: Context, orientation: Int) :
    DividerItemDecoration(context, orientation) {
    init {
        AppCompatResources.getDrawable(context, R.drawable.divider)?.let {
            setDrawable(it)
        }
    }
}