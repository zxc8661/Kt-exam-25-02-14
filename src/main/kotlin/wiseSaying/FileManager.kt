package org.example.wiseSaying

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


object FileManager {
    private val directory = File("db/wiseSaying").apply{
        if(!exists()) mkdirs()
    }

    val json: Json = Json{
        prettyPrint = true
        ignoreUnknownKeys = true
    }
    fun makeFile(wiseSaying: WiseSaying, lastId: Int) {

        File(directory,"${wiseSaying.id}.json").writeText(json.encodeToString(wiseSaying))
        File(directory,"lastId.txt").writeText(lastId.toString());
    }

    fun makeBuildFile(list: List<WiseSaying>) {

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

    fun readJsonFile(id: Int): WiseSaying? {

        val file = File(directory,"${id}.json")
        if (!file.exists()) return null

        val jsonString = file.readText()

        return try {
            json.decodeFromString<WiseSaying>(WiseSaying.serializer(), jsonString)
        } catch (e: Exception) {
            println("파싱오류")
            null
        }
    }

    fun modifyJsonFile(wiseSaying: WiseSaying){
        File(directory,"${wiseSaying.id}.json").writeText(json.encodeToString(wiseSaying))

    }

    fun deleteJsonFile(id:Int){
        val file = File(directory,"${id}.json")
        if(!file.exists()) return
        file.delete()
    }

}