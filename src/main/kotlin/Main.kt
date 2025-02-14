package org.example

fun main() {
    println("명언앱 시작")
    var lastId=0
    val Datas = mutableListOf<Data>()


    while(true){
        print("명령) ")
        val input = readlnOrNull()!!.trim().split("?","=")  //!!의 의미 : 해당 객체는 null 일리 없으니 null 처리 하지마


        if(input[0]=="종료") {
            break
        }else if(input[0] == "등록"){
            print("명언 : ")
            val content = readlnOrNull()!!.trim()
            print("작가 : ")
            val author = readlnOrNull()!!.trim()

            val id=++lastId
           Datas.add(Data(id,content,author))
        }else if(input[0]== "목록") {
            println("----------------------")
            Datas.asReversed().forEach{println("${it.id} / ${it.author} / ${it.content}")}
        }else if(input[0] == "삭제"){
           val result = Datas.removeAll{it.id==input[2].toInt()}

            if(result) {
                println("${input[2]}번 명언이 삭제되었습니다.")
            }else{
                println("${input[2]}번 명언이 존재하지 않습니다.")
            }
        }else if(input[0] == "수정"){
            if(input.size<2){
                println("잘못됨 입력입니다. 명령어를 다시 입력해주세요")
                continue
            }
            val modifyDataId = input[2].toInt()
            val preData = Datas.find{it.id==modifyDataId}

            if(preData==null){
                println("해당 id의 명언을 찾을 수 없습니다. 명령어를 다시 입력해 주세요")
                continue
            }

            println("명언(기존) : ${preData.content}")
            print("명언 : ")
            val newComment = readlnOrNull()!!.trim()
            println("작가(기존) : ${preData.author}")
            print("작가 : ")
            val newAuthor = readlnOrNull()!!.trim()

            Datas[Datas.indexOf(preData)]  = Datas[Datas.indexOf(preData)].copy(
                content = newComment,
                author = newAuthor
            )



        }
    }
}

data class Data ( val id: Int,val content: String,val  author:String)

