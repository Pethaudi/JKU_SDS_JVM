package digger

import dao.CsvWorker
import entities.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BasicSizes {
    companion object {

        val appearances = CsvWorker().getAllAppearances()
        val pokemons = CsvWorker().getAllPokemons()
        val days = appearances.map { it.dateofspawn }.toSet().toList()

        fun getAllContinents() = appearances.map { ap -> ap.continent!! }.toSet().toList()

        fun getAppearancesPerContinent(): List<NameCounter> {
            val res = mutableListOf<NameCounter>()

            for (i in getAllContinents().indices) {
                res.add(NameCounter(getAllContinents()[i]))
            }

            appearances.forEach {
                res.forEach { obj ->
                    if (obj.name == it.continent)
                        obj.counter++
                }
            }

            return res
        }

        fun getAppearancesPerContinentPercentage(): List<NamePercentage> {
            val res = mutableListOf<NamePercentage>()
            var sum = appearances.size

            getAppearancesPerContinent().forEach {
                res.add(NamePercentage(it.name, it.calcPercentage(sum)))
            }

            return res
        }

        ////////////////////// Pokemontable

        fun getAllPokemonTypes() = pokemons.map { it.type }.toSet().toList().filter { it != null }.map { it!! }

        fun getPokemon(id: Number) = pokemons.find { it.id == id }

        fun getAppearancesPerTypes() : List<NameCounter> {
            val res = mutableListOf<NameCounter>()
            getAllPokemonTypes().forEach {

                val tmp = NameCounter(it)

                appearances.forEach {a ->
                    val poke = getPokemon(a.pokemonid!!)

                    if(poke != null){
                        if(poke.type == it) tmp.counter++
                    }
                }

                res.add(tmp)
            }

            return res
        }

        fun getAppearancesPerDays(): List<NameCounter> {
            val res = mutableListOf<NameCounter>()

            val days = appearances.map { appearance -> appearance.dateofspawn!! }.toSet().toList()
            days.forEach {
                val tmp = NameCounter(it)
                appearances.forEach {ap ->
                    if(ap.dateofspawn == it) tmp.counter++
                }
                res.add(tmp)
            }

            return res
        }

        fun getAppearancesPerHour(): List<NameCounter> {
            val dtf = DateTimeFormatter.ofPattern("HH:mm:ss")
            val res = mutableListOf<NameCounter>()
            val times = appearances.map { LocalTime.parse(it.time, dtf).hour }

            times.toSet()
                    .forEach { res.add(NameCounter(it.toString())) }

            times.forEach {
                for(elem in res){
                    if(elem.name == it.toString()){
                        elem.counter++
                        break
                    }
                }
            }

            return res
        }

        fun getAppearancesPerTerrain(): List<NameCounter>{

            println("Starting the methode")
            val res = mutableListOf<NameCounter>()
            appearances.map { it.terrain!! }.toSet().toList().forEach { res.add(NameCounter(it)) }

            println(res.size)

            appearances.forEach {
                for(terrain in res){
                    if(terrain.name == it.terrain){
                        terrain.counter++
                        break
                    }
                }
            }

            return res
        }
    }
}