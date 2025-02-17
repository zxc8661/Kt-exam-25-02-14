import org.example.wiseSaying.App
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream


object TestRunner{
    private val originalIn: InputStream = System.`in`
    private val originalOut: PrintStream = System.out

    fun run(input:String):String{
        System.setIn(ByteArrayInputStream(("${input.trimIndent()}\n종료").toByteArray()))

        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        System.setOut(printStream)
        val app =App()
        app.clearData()
        app.run()

        val result = outputStream.toString().trim().replace(Regex("\\r\\n"),"\n")

        System.setIn(originalIn)
        System.setOut(originalOut)
        return result
    }
}