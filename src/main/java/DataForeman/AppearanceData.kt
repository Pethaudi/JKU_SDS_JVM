package DataForeman

import entities.Appearance
import java.io.File

/*
This class just splits the PokemonData.csv into other files
 */
class AppearanceData {
    fun start(){
        val basefile = File("PokemonData.csv").readLines()
        val commandfiles = mutableListOf<MutableList<String>>()
        var commands = mutableListOf<String>()
        for(i in basefile.indices){
            if(i % 50000 == 0  || i + 1 == basefile.count()){
                commandfiles.add(commands)
                commands = mutableListOf()
            }
            if(i != 0){
                //commands.add(JKU_SDS_Entry.generateSqlCommand(basefile[i]))
            }
        }

        createFiles(commandfiles)
    }

    private fun splitFile(){
        val basefile = File("PokemonData.csv").readLines()
        val splitter = mutableListOf<MutableList<String>>()

        var tmp = mutableListOf<String>()

        for(i in basefile.indices){
            if (i % 1000 == 0 || i + 1 == basefile.count()){
                splitter.add(tmp)
                tmp = mutableListOf()
                tmp.add(basefile[0])
            }
            else {
                tmp.add(basefile[i])
            }
        }

        createFiles(splitter)
    }

    private fun createFiles(splitter: List<List<String>>){
        for(i in splitter.indices){
            val newFile = File("pokemondata_$i.csv")

            if(newFile.exists()) newFile.delete()
            newFile.createNewFile()

            newFile.writeText(splitter[i].joinToString("\n"))
        }
    }
}