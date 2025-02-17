package org.example.wiseSaying

class WiseSayingController{
    val service = WiseSayingService()
    val errorMessage ="잘못된 입력입니다. 다시 입력해 주세요"
    fun loadInitialData(){
        service.loadInitialData()
    }
    fun wiseSayingController(input:Map<String,String>):Boolean{



        return when(input["cmd"]){
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

                if(input["keywordType"]!=null || input["keyword"]!=null){

                    println("----------------------")
                    println("검색어 타입 : ${input.get("keywordType")}")
                    println("검색어 : ${input.get("keyword")}")
                    println("----------------------")
                    println("번호 / 작가 / 명언")
                    println("----------------------")
                    service.keyList(input["keywordType"]!!,input["keyword"]!!).asReversed()
                        .forEach{println("${it.id} / ${it.author} / ${it.content}")}

                }else {
                    println("번호 / 작가 / 명언")
                    println("----------------------")

                    val page = input.get("page")?.toInt() ?:1
                    val totalPage:Int = service.totalPage();

                    service.list(page).forEach { println("${it.id} / ${it.author} / ${it.content}") }
                    println("페이지 : ${page} / [${totalPage}] ")

                }
                false
            }
            "삭제"->{

                if(!input.containsKey("id")){
                    println(errorMessage)
                }else{
                    val id = input["id"]!!.toIntOrNull()
                    if(id==null){
                        println(errorMessage)
                    }else{
                        println(service.remove(id = id))
                    }
                }
                false
            }
            "수정"->{
                if(!input.containsKey("id")){
                    println(errorMessage)
                } else {
                    val id = input["id"]!!.toIntOrNull()
                    if(id==null){
                        println(errorMessage)
                    }else{
                        val preWiseSaying = service.findWiseSaying(id)
                        if(preWiseSaying == null){
                            println("$id:를 찾을 수 없습니다. 다시 입력해주세요")
                        } else{
                            println("명언(기존) : ${preWiseSaying.content}")
                            print("명언 : ")
                            val newContent = readlnOrNull()!!.trim()
                            println("작가(기존) : ${preWiseSaying.author}" )
                            print("작가 : ")
                            val newAuthor = readlnOrNull()!!.trim()
                            service.modify(id,newAuthor,newContent)
                        }
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