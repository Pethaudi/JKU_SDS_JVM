package dao

import entities.Appearance
import entities.Pokemon
import entities.SpawnedTogether
import java.io.File
import kotlin.system.measureTimeMillis

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

    fun getAllPokemons() = File("data/PokemonData.csv").readLines().drop(1).map { Pokemon.generateFromCsv(it) }.toList()

    fun getAllAppearances() = File("data/Appearance.csv").readLines().drop(1).map { Appearance.generateFromCsv(it) }.toList()

    fun getAllSpawnedTogether() = File("data/SpawnedTogether.csv")
            .readLines()
            .drop(1)
            .map {
                val res = mutableListOf<Boolean>()
                val data = it.split(";")
                data.forEach {
                    if(it == "1")
                        res.add(res.size, true)
                    else res.add(res.size, false)
                }
                SpawnedTogether(res)
            }
            .toList()

    fun measureReadInTime() = measureTimeMillis { getAllPokemons(); getAllAppearances() }
}