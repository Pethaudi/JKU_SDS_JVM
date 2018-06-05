import DataForeman.AppearanceData
import dao.CsvWorker
import dao.DbWorker

fun main(args: Array<String>){
    //AppearanceData().start()
    //DBWorker.testMe()
    val current = System.currentTimeMillis()
    val worker = CsvWorker()
    println("millisec: ${worker.measureReadInTime()}")
}