package otus.gpb.recyclerview.domain

class LoadDataUseCase(val repository: Repository) {
    fun load(countData: Int){
        repository.loadData(countData)
    }
}