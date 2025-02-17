package org.example.wiseSaying

class App() {
    fun run(){
        println("명언앱 시작")
        val controller = WiseSayingController()
        controller.loadInitialData()

        while (true) {
            print("명령) ")


            val input = readlnOrNull()!!.trim().split("?", "=","&")

            val inputMap = mutableMapOf("cmd" to input[0])

           for(i in 1 until input.size step 2){
              if(i+1<input.size){
                  inputMap.put(input[i] , input[i+1])
              }
           }

            if (input.size < 1) {
                println("잘못된 입력닙니다. 다시입력해주세요")
                continue
            }
            val isExit = controller.wiseSayingController(inputMap)
            if(isExit) break
        }
    }

    fun clearData(){
        val repository = WiseSayingRepository()
        repository.clearData()

    }
}