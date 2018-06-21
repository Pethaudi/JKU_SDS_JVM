package entities.sorted

import dao.CsvWorker
import entities.Appearance
import entities.IJson
import entities.NameCounter
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.json.Json
import javax.json.JsonArray
import javax.json.JsonObject

object AppearancesPerDayPerHour {
    fun start() : JsonArray{
        val builder = Json.createArrayBuilder()

        val appearances = CsvWorker().getAllAppearances()

        val res = mutableListOf<Day>()
        val dayscheme = mutableListOf<NameCounter>()

        for(i in 0..23)
            dayscheme.add(NameCounter(i.toString(), 0))

        appearances.map { it.dateofspawn }
                .toSet()
                .forEach { res.add(Day(it!!, dayscheme)) }

        handleAppearances(appearances, res)

        res.forEach{
            builder.add(Day.toJson(it))
        }

        return builder.build()
    }

    private fun handleAppearances(appearances: List<Appearance>, days: List<Day>){

        val dtf = DateTimeFormatter.ofPattern("HH:mm:ss")
        var wasfound = false

        appearances.forEach { app ->

            for(day in days){

                if(day.day == app.dateofspawn){
                    for(hourcounter in day.hours){
                        val hour = LocalTime.parse(app.time, dtf).hour
                        if(hourcounter.name == hour.toString()){
                            hourcounter.counter++
                            wasfound = true
                            break
                        }
                    }
                }

                if(wasfound)
                    break
            }

            wasfound = true
        }
    }

    class Day(
            val day: String,
            var hours: List<NameCounter>
    ){
        companion object : IJson {
            override fun toJson(obj: Any): JsonObject {
                val builder = Json.createObjectBuilder()

                if(obj is Day){
                    val arrbuilder = Json.createArrayBuilder()
                    obj.hours!!.forEach { arrbuilder.add(NameCounter.toJson(it)) }
                    builder.add("hours", arrbuilder.build())
                    builder.add("day", obj.day)
                }

                return builder.build()
            }

            override fun toObject(json: JsonObject): Any {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}