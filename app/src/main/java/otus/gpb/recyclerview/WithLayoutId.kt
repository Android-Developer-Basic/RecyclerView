package otus.gpb.recyclerview

import androidx.annotation.LayoutRes

interface WithLayoutId {
    @get:LayoutRes
    val layoutId: Int
}