import DataForeman.AppearanceData
import dao.CsvWorker
import dao.DbWorker

fun main(args: Array<String>){
    //AppearanceData().start()
    //DBWorker.testMe()
    val worker = CsvWorker()
    //worker.insertPokemons()
    worker.insertAppearances()
}