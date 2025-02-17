package org.example.wiseSaying

class WiseSayingController{
    val service = WiseSayingService()
    val errorMessage ="잘못된 입력입니다. 다시 입력해 주세요"
    fun loadInitialData(){
        service.loadInitialData()
    }
    fun wiseSayingController(input:List<String>):Boolean{



        return when(input[0]){
            "종료"->{
                println("프로그램이 종료됩니다")
                true
            }
            "등록"->{
                print("명언 : ")
                val newContent = readlnOrNull()!!.trim()
                print("작가 : ")
                val newAuthor = readlnOrNull()!!.trim()
                val newWiseSayingId = service.create(newAuthor,newContent)
                println("${newWiseSayingId}번 명언이 등록되었습니다.")
                false
            }
            "목록"->{
                println("번호 / 작가 / 명언")
                println("----------------------")
                service.list().asReversed().forEach{println("${it.id} / ${it.author} / ${it.content}")}
                false
            }
            "삭제"->{
                if(input.size<2){
                    println(errorMessage)
                }else {
                    println(service.remove(id = input[2].toInt()))
                }
                false
            }
            "수정"->{
                if(input.size<2){
                    println(errorMessage)
                }else{
                    val preWiseSaying = service.findWiseSaying(input[2].toInt())

                    if(preWiseSaying==null){
                        println("${input[2]}를 찾을 수 없습니다. 다시 입력해주세요.")
                    }else{
                        println("명언(기존) : ${preWiseSaying.content}")
                        print("명언 : ")
                        val newContest = readlnOrNull()!!.trim()
                        println("작가(기존) : ${preWiseSaying.author}")
                        print("작가 : ")
                        val newAuthor = readlnOrNull()!!.trim()

                        service.modify(input[2].toInt(),newAuthor,newContest)
                    }
                }
                false
            }
            "빌드"->{
                service.build()
                println("data.json 파일의 내용이 갱신되었습니다.")
                false
            }
            else->{
                println(errorMessage)
                false
            }
        }

    }
}