import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class AppTest {
    @Test
    @DisplayName("명언 작성")
    fun t1(){
        val result=TestRunner.run(
            """
                등록
                나의 죽음을 적들에게 알리지 마라.
                충무공 이순신
            """
        )
        println("result: $result")
        assertContains(result,"명언 : ")
        assertContains(result,"작가 : ")
        assertContains(result,"번 명언이 등록되었습니다.")
    }

    @Test
    @DisplayName("목록")
    fun t2(){
        val result=TestRunner.run(
            """
                등록
                나의 죽음을 적들에게 알리지 마라
                춤무공 이순신
                목록
            """
        )
        println("result: $result")
        assertContains(result,"1 / 춤무공 이순신 / 나의 죽음을 적들에게 알리지 마라")

    }

    @Test
    @DisplayName("삭제")
    fun t3(){
        val result=TestRunner.run(
            """
               등록
                나의 죽음을 적들에게 알리지 마라
                충무공 이순신
                삭제?id=1
            """
        )
        println("result: $result")
        assertContains(result,"1번 명언이 삭제되었습니다")
    }

    @Test
    @DisplayName("수정")
    fun t4(){
        val result = TestRunner.run(
            """
                등록
                나의 죽음을 적들에게 알리지 마라
                충무공 이순신
                수정?id=1
                수정 명언
                수정 작가
                목록
            """
        )
        println("result: $result")
        assertContains(result,"1 / 수정 작가 / 수정 명언")
    }

    @Test
    @DisplayName("빌드")
    fun t5(){
        val result = TestRunner.run(
            """
                빌드
            """.trimIndent()
        )
        println("result: $result")
        assertContains(result,"data.json")
    }

}