package otus.gpb.recyclerview

import otus.gpb.recyclerview.model.MainModel
import kotlin.random.Random

class MainPresenter {

    private val avatarList = listOf(R.drawable.avatar,R.drawable.avatar0,R.drawable.avatar01,R.drawable.avatar02,R.drawable.avatar03,R.drawable.avatar05)
    private val previewList = listOf(null,null,null,R.drawable.preview)
    private val checkList = listOf(null,R.drawable.check_icon,R.drawable.read_icon)
    private val timeList = listOf("11:38 AM","12:44 AM","Fri","Thu","Wed","May 02")

    fun getItems(startPosition: Int = 0): List<MainModel> {
        val mutableList = mutableListOf<MainModel>()
        for (i in 1..10) {
            val model = MainModel(
                id = i,
                avatar = avatarList.random(),
                preview = previewList.random(),
                nick =  "Some nick ${i + startPosition}",
                status =  "status ${i + startPosition}",
                last_massage =  "last_massage ${i + startPosition}",
                time = timeList.random(),
                count =  Random.nextInt(0, 20),
                check =  checkList.random(),
                isSCAM = Random.nextBoolean(),
                isMute = Random.nextBoolean(),
                isVerified = Random.nextBoolean()
            )
            mutableList.add(model)
        }
        return mutableList
    }
}