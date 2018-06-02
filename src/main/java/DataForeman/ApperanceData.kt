package DataForeman

import java.io.File

/*
This class just splits the PokemonData.csv into other files
 */
class ApperanceData {
    fun start(){
        val basefile = File("PokemonData.csv").readLines()
        val splitter = mutableListOf<MutableList<String>>()

        var tmp = mutableListOf<String>()

        for(i in basefile.indices){
            if(i == 0){

            }
            else if (i % 100000 == 0){
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