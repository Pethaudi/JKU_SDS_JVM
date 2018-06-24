import dao.CsvWorker
import dao.DbWorker
import dao.JsonWorker
import digger.AppearancesPerDayWithCoordinates
import digger.BasicSizes
import entities.*
import entities.sorted.AppearancesPerDayPerHour
import java.io.File

val web_hauer = "/Users/peterhauer/Desktop/ProgrammingStuff/DataScience/JKU_SDS_WEB/src/data"
val web_oberaigner = "/Users/maxoberaigner/Desktop/JKU_SDS_WEB/src/data"

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

    /*JsonWorker.writeToFile("AppearancesPerContinent",
            BasicSizes.getAppearancesPerContinent().map { AppearanceContinent.toJson(it) })*/

    /*JsonWorker.writeToFile("AppearancesPerContinentPercentage",
            BasicSizes.getAppearancesPerContinentPercentage().map { NamePercentage.toJson(it) })*/

    /*JsonWorker.writeToFile("Appearances",
            BasicSizes.appearances.map {
                try{Appearance.toJson(it)}
                catch(ex: Exception) {null}
            }.filter { p -> p != null }.map { j -> j!! })*/

    //JsonWorker.writeToFile("AppearancesPerType", BasicSizes.getAppearancesPerTypes().map { NameCounter.toJson(it) })
    //JsonWorker.writeToFile("AppearancesPerDays", BasicSizes.getAppearancesPerDays().map { NameCounter.toJson(it) })

    //JsonWorker.writeToFile("AppearancesPerDayWithCoordinates", AppearancesPerDayWithCoordinates.start())

    //JsonWorker.writeToFile("AppearancesPerHour", BasicSizes.getAppearancesPerHour().map { NameCounter.toJson(it) })
    //JsonWorker.writeToFile("AppearancesPerDayPerHour", AppearancesPerDayPerHour.start())

    /*
    - spawn per type
    - spawn per day
    - spawn per day per continent
    - spawn per daytime (hour)
    - spawn per daytime per day (hour)
    show spawns per daytime per day on google maps
    spawn per country
    continent
        city

    average weight
    spawntype per daytime
    are all pokemons spawned at least one time? (hi/lo)

    spawn more pokemon at one spot at a specific time
    calc nests (a spot where a high amount of one pokemon spawn
    calc whatever

    gyms errechnen
    pokestops errechnen
        -> density

    - erscheinungen der pokemon einzeichnen
    */
    //copyFilesToWeb(web_hauer)

    generateCustomGoogleMapsIcons()
}

fun copyFilesToWeb(path: String){
    val data = File("results")
    data.listFiles().forEach {
        val dest = File(path + "/" + it.name)
        it.copyTo(dest, true)
    }
}

/*
write once, never migrate
this methode just generates the JS code for the customized icons for google maps on the front end
 */
fun generateCustomGoogleMapsIcons(){
    val iconfile = File("icons.js")

    if(iconfile.exists())
        iconfile.delete()
    iconfile.createNewFile()

    iconfile.writeText(PreCalculatedForJS.getGoogleMapsIconString())
}