package org.example

fun main() {
    println("명언앱 시작")

    while(true){
        print("명언) ")
        val input = readlnOrNull()!!.trim()  //!!의 의미 : 해당 객체는 null 일리 없으니 null 처리 하지마


        if(input=="종료")
            break
    }
}