package org.example.wiseSaying
class WiseSayingService{
    val repository  = WiseSayingRepository()
    fun create(newAuthor: String, newContent: String):Int {
        return repository.create(newAuthor,newContent)

    }
    fun list(page:Int):List<WiseSaying>{
        return repository.list(page)
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

    fun keyList(keywordType: String, keyword: String):List<WiseSaying> {
        return repository.keyList(keywordType,keyword)
    }

    fun totalPage(): Int {
        return repository.totalPage()
    }


}
