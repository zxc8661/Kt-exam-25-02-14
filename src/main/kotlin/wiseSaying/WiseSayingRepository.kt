package org.example.wiseSaying

class WiseSayingRepository{
    val wiseSayings = WiseSayingList.wiseSayingList
    var lastId = WiseSayingList.lastId
    val fileManager = FileManager
    fun create(newAuthor: String, newContent: String):Int {
        lastId++
        var newWiseSaying = WiseSaying(lastId,newContent,newAuthor)
        wiseSayings.add(newWiseSaying)
        fileManager.makeFile(newWiseSaying,lastId)
        return lastId
    }

    fun list():List<WiseSaying>{
        return wiseSayings
    }

    fun remove(id: Int):String {
        val result = wiseSayings.removeAll{it.id == id}
        if(result){
            fileManager.deleteJsonFile(id)
            return "${id}번 명언이 삭제되었습니다"

        }else{
            return "${id}번 명언이 존재하지 않습니다."
        }
    }

    fun findWiseSaying(id : Int): WiseSaying? {
        val preWiseSaying:WiseSaying? = wiseSayings.find{it.id==id}
        return preWiseSaying
    }

    fun modify(id: Int, newAuthor: String, newContest: String) {
        val index = wiseSayings.indexOfFirst { it.id==id }
        wiseSayings[index] = wiseSayings[index].copy(
            content = newContest,
            author = newAuthor
        )
        fileManager.modifyJsonFile(wiseSayings[index])
    }

    fun build() {
        fileManager.makeBuildFile(wiseSayings)
    }

    fun loadInitialData() {
        lastId = fileManager.readTxtFile()?:0

        for(i in 1..lastId){
            val data = fileManager.readJsonFile(i)
            if(data!=null){
                wiseSayings.add(data)
            }
        }
    }

    fun clearData(){
        lastId = fileManager.readTxtFile()?:0

        for(i in 1..lastId){
            fileManager.deleteJsonFile(i)
        }

        fileManager.makeLastId()
    }



}