package otus.gpb.recyclerview

class ListChat {

    fun getList(startPosition: Int = 0): MutableList<movieModel> {

        val list = mutableListOf<movieModel>(
            movieModel(
                id = 1,
                avatar = R.drawable.avatar8,
                name = "Pizza",
                author = "jija",
                message = "Yes, they are necessary",
                time = "11:38 AM",
                isMute = true,
                isPreview = true
            ), movieModel(
                id = 2,
                avatar = R.drawable.avatar7,
                name = "Elon",
                message = "I love/r/Reddit!",
                time = "12:44 AM",
                isMute = true
            ), movieModel(
                id = 3,
                avatar = R.drawable.avatar3,
                name = "Pasha",
                message = "How are u?",
                time = "Fri",
                isVerified = true,
                isMute = true
            ), movieModel(
                id = 4,
                avatar = R.drawable.tel,
                name = "Telegram Support",
                author = "Support",
                message = "Yes it happend.",
                time = "Thu",
                isVerified = true,
                isRead = true,
                cont = 1
            ), movieModel(
                id = 5,
                avatar = R.drawable.avatar4,
                name = "Karina",
                message = "Okay",
                time = "Wed",
                isCheck = true
            ), movieModel(
                id = 6,
                avatar = R.drawable.avatar1,
                name = "Marlin",
                message = "Will it never happen",
                time = "May 02",
                isScam = true,
                isRead = true
            ), movieModel(
                id = 7,
                avatar = R.drawable.avatar8,
                name = "Pizza",
                author = "jija",
                message = "Yes, they are necessary",
                time = "11:38 AM",
                isMute = true,
                isPreview = true
            ), movieModel(
                id = 8,
                avatar = R.drawable.avatar7,
                name = "Elon",
                message = "I love/r/Reddit!",
                time = "12:44 AM",
                isMute = true
            ), movieModel(
                id = 9,
                avatar = R.drawable.avatar4,
                name = "Pasha",
                message = "How are u?",
                time = "Fri",
                isVerified = true,
                isMute = true
            ), movieModel(
                id = 10,
                avatar = R.drawable.tel,
                name = "Telegram Support",
                author = "Support",
                message = "Yes it happend.",
                time = "Thu",
                isVerified = true,
                isRead = true,
                cont = 1
            )
        )
        return list
    }
}