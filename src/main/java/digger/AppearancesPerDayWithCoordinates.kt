package digger

import dao.CsvWorker
import entities.Coordinate
import entities.sorted.AppearancesPerDayWithCoordinates
import javax.json.Json
import javax.json.JsonArray

class AppearancesPerDayWithCoordinates {
    companion object {

        fun start(): JsonArray {
            val builder = Json.createArrayBuilder()

            BasicSizes.days.forEach {
                if(it != null){
                    val spawns = mutableListOf<Coordinate>()
                    getPokemonsPerDay(it).forEach { poke ->
                        spawns.add(Coordinate(poke.pokemonid!!.toInt(), poke.degreeOfLongitude?.toDouble() ?: 0.0, poke.degreeOfLatitude?.toDouble() ?: 0.0))
                    }

                    builder.add(AppearancesPerDayWithCoordinates.toJson(AppearancesPerDayWithCoordinates(it, spawns)))
                }
            }

            return builder.build()
        }

        private fun getPokemonsPerDay(day: String) = BasicSizes.appearances.filter { it.dateofspawn == day }
    }
}