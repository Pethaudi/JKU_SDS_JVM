import DataForeman.AppearanceData
import dao.CsvWorker
import dao.DbWorker
import dao.JsonWorker
import digger.BasicSizes
import entities.AppearanceContinent
import entities.NamePercentage
import java.io.File

val web_hauer = "/Users/peterhauer/Desktop/ProgrammingStuff/DataScience/JKU_SDS_WEB/src/data"
val web_oberaigner = ""

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

    copyFilesToWeb(web_hauer)
}

fun copyFilesToWeb(path: String){
    val data = File("results")
    data.listFiles().forEach {
        val dest = File(path + "/" + it.name)
        it.copyTo(dest, true)
    }
}