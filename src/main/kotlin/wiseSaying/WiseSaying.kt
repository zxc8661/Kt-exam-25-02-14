package org.example.wiseSaying

import kotlinx.serialization.Serializable

@Serializable
data class WiseSaying(val id:Int, val content:String, val author:String)
