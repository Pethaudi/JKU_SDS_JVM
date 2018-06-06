package digger

import dao.CsvWorker
import entities.*

class BasicSizes {
    companion object {

        val appearances: List<Appearance>
        val pokemons: List<Pokemon>
        private var continents: List<String>? = null
        private var appearancesPerContinent: List<AppearanceContinent>? = null

        init {
            this.appearances = CsvWorker().getAllAppearances()
            this.pokemons = CsvWorker().getAllPokemons()
        }

        fun getAllContinents() : List<String> {
            if(this.continents == null){
                continents = appearances.map { ap -> ap.continent!! }.toSet().toList()
            }

            return continents!!
        }

        fun getAppearancesPerContinent(): List<AppearanceContinent> {
            if(this.appearancesPerContinent == null){
                val res = mutableListOf<AppearanceContinent>()

                for(i in getAllContinents().indices){
                    res.add(AppearanceContinent(getAllContinents()[i]))
                }

                appearances.forEach {
                    res.forEach{ obj ->
                        if(obj.continent == it.continent)
                            obj.counter++
                    }
                }

                this.appearancesPerContinent = res
            }

            return this.appearancesPerContinent!!
        }

        fun getAppearancesPerContinentPercentage(): List<NamePercentage> {
            val res = mutableListOf<NamePercentage>()
            var sum = appearances.size

            getAppearancesPerContinent().forEach {
                res.add(NamePercentage(it.continent, it.calcPercentage(sum)))
            }

            return res
        }

        ////////////////////// Pokemontable

        fun getAllTypes() = pokemons.map { it.type }.toSet().toList().filter { it != null }.map { it!! }

        fun getPokemon(id: Number) = pokemons.find { it.id == id }

        fun getAppearancesPerTypes() : List<NameCounter> {
            val res = mutableListOf<NameCounter>()
            getAllTypes().forEach {

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
    }
}