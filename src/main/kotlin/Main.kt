package org.example

fun main() {
    println("명언앱 시작")
    var lastId=0
    val Datas = mutableListOf<Data>()


    while(true){
        print("명언) ")
        val input = readlnOrNull()!!.trim()  //!!의 의미 : 해당 객체는 null 일리 없으니 null 처리 하지마


        if(input=="종료") {
            break
        }else if(input == "등록"){
            print("명언 : ")
            val content = readlnOrNull()!!.trim()
            print("작가: ")
            val author = readlnOrNull()!!.trim()

            val id=++lastId
           Datas.add(Data(id,content,author))
        }else if(input== "목록"){
            println("----------------------")
            for(data in Datas){
                println("${data.id} / ${data.author} / ${data.content}" )
            }
        }
    }
}

data class Data ( val id: Int,val content: String,val  author:String)