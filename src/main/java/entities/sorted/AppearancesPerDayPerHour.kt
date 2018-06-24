package entities.sorted

import dao.CsvWorker
import entities.Appearance
import entities.Coordinate
import entities.IJson
import entities.NameCounter
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.json.Json
import javax.json.JsonArray
import javax.json.JsonObject

object AppearancesPerDayPerHour {
    val dtf = DateTimeFormatter.ofPattern("HH:mm:ss")

    fun startClassic() : JsonArray{
        val builder = Json.createArrayBuilder()

        val appearances = CsvWorker().getAllAppearances()

        val res = mutableListOf<Day>()

        appearances.map { it.dateofspawn }
                .toSet()
                .forEach {

                    val dayscheme = mutableListOf<NameCounter>()

                    for(i in 0..23)
                        dayscheme.add(NameCounter(i.toString(), 0))

                    res.add(Day(it!!, dayscheme))
                }

        handleAppearances(appearances, res)

        res.forEach{
            builder.add(Day.toJson(it))
        }

        return builder.build()
    }

    private fun handleAppearances(appearances: List<Appearance>, days: List<Day>){

        appearances.forEach { app ->
            for(day in days){
                if(day.day == app.dateofspawn){
                    for(hourcounter in day.hours){
                        val hour = LocalTime.parse(app.time, dtf).hour

                        if(hourcounter.name == hour.toString()){
                            hourcounter.counter++
                            break
                        }
                    }
                }
            }
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

    fun startWithCoordinates() : JsonArray {
        val builder = Json.createArrayBuilder()

        val appearances = CsvWorker().getAllAppearances()

        val days = mutableListOf<DayWithHours>()

        appearances.map { it.dateofspawn }
                .toSet()
                .forEach {
                    val dayscheme = mutableListOf<Hour>()

                    for(i in 0..23)
                        dayscheme.add(Hour(i.toString(), mutableListOf()))

                    days.add(DayWithHours(it!!, dayscheme))
                }

        appearances.forEach { app ->
            for(day in days){
                if(day.day == app.dateofspawn){
                    for(dayhour in day.hours){
                        val hour = LocalTime.parse(app.time, dtf).hour

                        if(dayhour.hour == hour.toString()){
                            dayhour.apperances.add(
                                    Coordinate(app.pokemonid?.toInt() ?: 0,
                                    app.degreeOfLongitude?.toDouble() ?: 0.0,
                                    app.degreeOfLatitude?.toDouble() ?: 0.0))
                            break
                        }
                    }
                }
            }
        }

        days.forEach { builder.add(DayWithHours.toJson(it)) }

        return builder.build()
    }

    class Hour(val hour: String, var apperances: MutableList<Coordinate>){
        companion object : IJson {
            override fun toJson(obj: Any): JsonObject {
                val builder = Json.createObjectBuilder()

                if(obj is Hour){
                    builder.add("hour", obj.hour)
                    val arraybuilder = Json.createArrayBuilder()

                    obj.apperances.forEach { arraybuilder.add(Coordinate.toJson(it)) }

                    builder.add("appearances", arraybuilder.build())
                }

                return builder.build()
            }

            override fun toObject(json: JsonObject): Any {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
    class DayWithHours(val day: String, val hours: List<Hour>) {
        companion object : IJson {
            override fun toJson(obj: Any): JsonObject {
                val builder = Json.createObjectBuilder()

                if(obj is DayWithHours){
                    builder.add("day", obj.day)

                    val arraybuilder = Json.createArrayBuilder()

                    obj.hours.forEach { arraybuilder.add(Hour.toJson(it)) }

                    builder.add("hours", arraybuilder.build())
                }

                return builder.build()
            }

            override fun toObject(json: JsonObject): Any {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}