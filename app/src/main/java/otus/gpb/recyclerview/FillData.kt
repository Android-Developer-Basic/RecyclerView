package otus.gpb.recyclerview

class FillData {
    fun getData(): MutableList<ItemData> {
        return mutableListOf(
            ItemData(R.drawable.avatar, "Pizza", "Yes, they are necessary", "11:38 AM", "jija",
                true, false, false, false, false, 0, false )
        )

    }
}