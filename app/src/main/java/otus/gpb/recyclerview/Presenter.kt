package otus.gpb.recyclerview

import otus.gpb.recyclerview.model.movieModel
import kotlin.random.Random

class Presenter {
    private val avatarList = listOf(R.drawable.avatar1, R.drawable.pasha, R.drawable.elon, R.drawable.gerl,R.drawable.merelin,R.drawable.tg) //список из имен
    private val previewList = listOf(null,null,null,R.drawable.preview)                             //привью
    private val checkList = listOf(null,R.drawable.check,R.drawable.too_check)                      //прочитано не прачитано
    private val timeList = listOf("11:38 AM","12:44 AM","Fri","Thu","Wed","May 02")                 //список из дат

    fun getItems(startPosition: Int = 0): List<movieModel> {
        val mutableList = mutableListOf<movieModel>()
        for (i in 1..20) {
            val model = movieModel(
                id = i,
                avatar = avatarList.random(),
                preview = previewList.random(),
                nick =  "Some nick ${i + startPosition}",
                status =  "status $i",
                last_massage =  "last_massage $i",
                time = timeList.random(),
                count =  Random.nextInt(0, 10),
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

 //генерируем данные