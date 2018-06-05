package digger

import dao.CsvWorker
import entities.Appearance
import entities.AppearanceContinent
import entities.NamePercentage
import entities.Pokemon

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
            var sum = getAppearancesPerContinent().size
            getAppearancesPerContinent().forEach {
                res.add(NamePercentage(it.continent, it.calcPercentage(sum)))
            }
            return res
        }
    }
}