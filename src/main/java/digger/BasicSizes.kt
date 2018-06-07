package digger

import dao.CsvWorker
import entities.*

class BasicSizes {
    companion object {

        val appearances = CsvWorker().getAllAppearances()
        val pokemons = CsvWorker().getAllPokemons()

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
    }
}