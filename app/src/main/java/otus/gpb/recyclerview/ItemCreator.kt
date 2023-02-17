package otus.gpb.recyclerview

class ItemCreator {

    private val firstNameList = listOf("Michael", "Noah", "Christian",
        "Jesus", "Thomas", "Christopher", "Maria", "Rebecca", "Holy",
        "Joy", "Hanzo", "Lansy", "Boris")

    private val lastNameList = listOf("Castillon", "Crider", "Miller",
        "Lindsey", "Silverberg", "Powell", "Stapleton", "TBrown", "Quintero",
        "Vaughn", "Provencher", "Smith", "Sanders")

    fun getFullName() = "${firstNameList.random()} ${lastNameList.random()}"

    fun getStatus() = listOf("Support", "Chemist", "Programmer", "Driver",).random()

    fun getMessage() = listOf("Be kind whenever possible. It is always possible.", "Love is the flower you've got to let grow.",
        "They improved dramatically once the lead singer left.", "Be great in act, as you have been in thought.",
        "Freedom is the right to live as we wish.", "Ignorance never settle a question.", "Nobody loves a pig wearing lipstic",
        "Flying fish few by the space station.", "Everything in life is luck!").random()

    fun getBoolean() = listOf(true, false).random()


    fun getProfileImage() = listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3,
        R.drawable.avatar4, R.drawable.avatar5, R.drawable.avatar6).random()

    fun getCounterMessages() = listOf(5,8,2,4,23,12,16,7,9,6,3,17).random()

    fun getDate(): String {
        val hour = (10..23).random()
        val minutes = (10..59).random()
        val amPm = listOf("AM", "PM").random()
        return "$hour:$minutes $amPm"
    }
}