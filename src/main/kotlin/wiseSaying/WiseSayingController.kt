package org.example.wiseSaying

class WiseSayingController{

    fun wiseSayingController(input:List<String>):Boolean{

        return when(input[0]){
            "종료"->{
                println("프로그램이 종료됩니다")
                true
            }
            "등록"->{
                print("명언 : ")
                val content = readlnOrNull()!!.trim()
                print("작가 : ")
                val author = readlnOrNull()!!.trim()
                false
            }
            "목록"->{
                false
            }
            "삭제"->{
                false
            }
            "수정"->{
                false
            }
            "빌드"->{
                false
            }
            else->{
                false
            }
        }

    }
}