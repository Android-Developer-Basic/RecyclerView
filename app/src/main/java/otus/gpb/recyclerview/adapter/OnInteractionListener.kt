package otus.gpb.recyclerview.adapter

import otus.gpb.recyclerview.ChatItem

interface OnInteractionListener {
    fun onBindingClick(item: ChatItem, itemPosition:Int)
}