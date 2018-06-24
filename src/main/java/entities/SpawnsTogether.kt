package entities

import dao.CsvWorker
class SpawnsTogether {
    fun getSpawns() {
        var spawns = CsvWorker().getAllSpawnedTogether()
        var pokemons = CsvWorker().getAllPokemons()
        var cnt = 0
        var list = mutableListOf<Pokemon>()
        var result = mutableListOf<String>()
        var check = false
        var failcnt = 0
       spawns.forEach {
           cnt = 0
           list.clear()
           while(cnt < it.spawns.size){
               if(it.spawns.get(cnt) == true){
                   list.add(pokemons.get(cnt))
               }
               cnt++
           }
           if(list.size > 0) {
               var temp = list.get(0).category
               list.drop(0)
               list.forEach {
                   if (it.category == temp) {
                       check = true
                   }
               }
               if (check == true) {
                   result.add(temp.toString())
               } else {
                   result.add("noMatch")
               }
               check = false
           }
           else
               failcnt++
       }

        println(failcnt)


    }
}