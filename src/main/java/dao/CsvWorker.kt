package dao

import entities.Appearance
import entities.Pokemon
import java.io.File

class CsvWorker {
    fun insertPokemons(){
        val file = File("data/PokemonData.csv").readLines()
        file.drop(1).map { Pokemon.generateFromCsv(it) }
                .forEach { p -> DbWorker.add(p) }
    }

    fun insertAppearances(){
        val file = File("data/Appearance.csv").readLines()

        DbWorker.add(file.drop(1).map { s -> Appearance.generateFromCsv(s) }.toList())
    }
}