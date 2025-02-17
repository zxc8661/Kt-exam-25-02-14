package org.example.wiseSaying

import kotlinx.serialization.Serializable


object WiseSayingList{
    var lastId = 0
    var wiseSayingList:MutableList<WiseSaying> = mutableListOf()
}

@Serializable
data class WiseSaying(val id:Int, val content:String, val author:String)
