package org.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.FileManager.makeBuildFile
import org.example.FileManager.makeFile
import org.example.FileManager.readJsonFile
import org.example.FileManager.readTxtFile
import java.io.File
import kotlin.system.exitProcess

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


        when(input[0]){
            "종료" -> {
                println("프로그램이 종료됩니다")
                exitProcess(0)
            }
            "등록" ->{
                print("명언 : ")
                val content = readlnOrNull()!!.trim()
                print("작가 : ")
                val author = readlnOrNull()!!.trim()
                val id=++lastId
                val newData=Data(id,content,author)
                Datas.add(newData)
                makeFile(newData,lastId)
            }
            "목록" ->{
                println("----------------------")
                Datas.asReversed().forEach{println("${it.id} / ${it.author} / ${it.content}")}
            }
            "삭제" ->{
                val result = Datas.removeAll{it.id==input[2].toInt()}
                if(result) {
                    println("${input[2]}번 명언이 삭제되었습니다.")
                    FileManager.deleteJsonFile(input[2].toInt())
                }else{
                    println("${input[2]}번 명언이 존재하지 않습니다.")
                }
            }
            "수정" ->{
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
            "빌드" ->{
                makeBuildFile(Datas)
                println("data.json 파일의 내용이 갱신되었습니다")
            }
            else ->{
                println("알 수 없는 명령입니다")
            }
        }
    }
}

@Serializable
data class Data ( val id: Int,val content: String,val  author:String)

object FileManager {
    private val directory = File("db/wiseSaying").apply{
        if(!exists()) mkdirs()
    }

    val json: Json = Json{
        prettyPrint = true
        ignoreUnknownKeys = true
    }
    fun makeFile(data: Data, lastId: Int) {

        File(directory,"${data.id}.json").writeText(json.encodeToString(data))
        File(directory,"lastId.txt").writeText(lastId.toString());
    }

    fun makeBuildFile(list: List<Data>) {

        File(directory,"data.json").writeText(json.encodeToString(list))
    }


    fun readTxtFile(): Int? {
        val file = File(directory,"lastId.txt")
        if (!file.exists()) {
            println("파일이 존재하지 않습니다")
            return null
        }
        return file.readText().toInt()
    }

    fun readJsonFile(id: Int): Data? {

        val file = File(directory,"${id}.json")
        if (!file.exists()) return null

        val jsonString = file.readText()

        return try {
           json.decodeFromString<Data>(Data.serializer(), jsonString)
        } catch (e: Exception) {
            println("파싱오류")
            null
        }
    }

    fun deleteJsonFile(id:Int){
        val file = File(directory,"${id}.json")
        if(!file.exists()) return
        file.delete()
    }

}