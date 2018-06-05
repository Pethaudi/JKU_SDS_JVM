import DataForeman.AppearanceData
import dao.CsvWorker
import dao.DbWorker
import digger.BasicSizes

fun main(args: Array<String>){
    //AppearanceData().start()
    //DBWorker.testMe()
    val worker = CsvWorker()
    //println("millisec: ${worker.measureReadInTime()}")
    //BasicSizes.getAllContinents().forEach { println(it) }
    BasicSizes.getAppearancesPerContinent().forEach { println("${it.continent} ${it.calcPercentage(BasicSizes.appearances.count())}%") }
}