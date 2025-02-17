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

    fun list(page: Int):List<WiseSaying>{
        val  itemPerPage = 5
        val reversList= wiseSayings.asReversed()
        val startIndex=(page-1)*itemPerPage
        var endIndex=startIndex+itemPerPage
        if(endIndex>=reversList.size)endIndex = reversList.size


        return reversList.subList(startIndex,endIndex)
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

    fun keyList(keywordType: String, keyword: String):List<WiseSaying> {
        var list:MutableList<WiseSaying> = mutableListOf()
        when(keywordType){
                "content"->{
                    for(wiseSaying in wiseSayings){
                        if(wiseSaying.content.contains(keyword)){
                            list.add(wiseSaying)
                        }
                    }
                }
                "author"->{
                    for(wiseSaying in wiseSayings){
                        if(wiseSaying.author.contains(keyword)){
                            list.add(wiseSaying)
                        }
                    }
                }
            }
        return list
    }

    fun totalPage(): Int {
        var totalPage = wiseSayings.size/5
        if(wiseSayings.size%5!=0) totalPage+=1
        return totalPage
    }


}