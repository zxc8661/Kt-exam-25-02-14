package org.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    println("명언앱 시작")
    var lastId= readTxtFile() ?:0
    val Datas = mutableListOf<Data>()


    for(i in 1..lastId){
        val data = readJsonFile(i)
        if(data!=null) Datas.add(data)
    }


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
            val newData=Data(id,content,author)
           Datas.add(newData)
            makeFile(newData,lastId)
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

@Serializable
data class Data ( val id: Int,val content: String,val  author:String)

fun makeFile(data:Data, lastId:Int){
    val directory = File("db/wiseSaying")
    if(!directory.exists()) directory.mkdirs()

    val jsonString = Json {prettyPrint=true}.encodeToString(data)
    File("db/wiseSaying/${data.id}.json").writeText(jsonString)

    File("db/wiseSaying/lastId.txt").writeText(lastId.toString());
}

fun readTxtFile():Int?{
    val file = File("db/wiseSaying/lastId.txt")
    if(!file.exists()){
        println("파일이 존재하지 않습니다")
        return null
    }
    return file.readText().toInt()
}

fun readJsonFile(id:Int):Data?{

    val file = File("db/wiseSaying/${id}.json")
    if(!file.exists()) return null

    val jsonString = file.readText()

    return try{
        Json{ignoreUnknownKeys=true}.decodeFromString<Data>(Data.serializer(),jsonString)
    }catch (e:Exception){
        println("파싱오류")
        null
    }
}
