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
            print("작가: ")
            val author = readlnOrNull()!!.trim()

            val id=++lastId
           Datas.add(Data(id,content,author))
        }else if(input[0]== "목록") {
            println("----------------------")
            for (data in Datas) {
                println("${data.id} / ${data.author} / ${data.content}")
            }
        }else if(input[0] == "삭제"){
           val result = Datas.removeAll{it.id==input[2].toInt()}

            if(result) {
                println("${input[2]}번 명언이 삭제되었습니다.")
            }else{
                println("${input[2]}번 명언이 존재하지 않습니다.")
            }
        }
    }
}

data class Data ( val id: Int,val content: String,val  author:String)

