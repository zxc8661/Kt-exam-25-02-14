package org.example.wiseSaying
class WiseSayingService{
    val repository  = WiseSayingRepository()
    fun create(newAuthor: String, newContent: String) {
        repository.create(newAuthor,newContent)

    }
    fun list():List<WiseSaying>{
        return repository.list()
    }

    fun remove(id: Int):String {
        return repository.remove(id)
    }

    fun findWiseSaying(id:Int): WiseSaying? {
        return repository.findWiseSaying(id)
    }

    fun modify(id:Int, newAuthor: String, newContest: String) {
        repository.modify(id,newAuthor,newContest)
    }

    fun build() {
        repository.build()
    }

    fun loadInitialData() {
        repository.loadInitialData()
    }


}
