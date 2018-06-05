import DataForeman.AppearanceData
import dao.CsvWorker
import dao.DbWorker
import dao.JsonWorker
import digger.BasicSizes
import entities.AppearanceContinent
import entities.NamePercentage

fun main(args: Array<String>){
    //AppearanceData().start()
    //DBWorker.testMe()
    val worker = CsvWorker()
    //println("millisec: ${worker.measureReadInTime()}")
    //BasicSizes.getAllContinents().forEach { println(it) }
    /*BasicSizes.getAppearancesPerContinent()
            .forEach {
                println("${it.continent} ${it.calcPercentage(BasicSizes.appearances.count())}%")
            }*/

    /*val json = BasicSizes.getAppearancesPerContinent().map { AppearanceContinent.toJson(it) }
    JsonWorker.writeToFile("AppearancesPerContinent", json)*/

    //worker.insertAppearances()

    JsonWorker.writeToFile("AppearancesPerContinent",
            BasicSizes.getAppearancesPerContinent().map { AppearanceContinent.toJson(it) })

    JsonWorker.writeToFile("AppearancesPerContinentPercentage",
            BasicSizes.getAppearancesPerContinentPercentage().map { NamePercentage.toJson(it) })
}