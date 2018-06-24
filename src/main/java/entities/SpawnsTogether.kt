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
       spawns.forEach {
           cnt = 0
           list = mutableListOf<Pokemon>()
           while(cnt < it.spawns.size){
               if(it.spawns.get(cnt) == true){
                   list.add(pokemons.get(cnt))
               }
               cnt++
           }
           cnt=1
           var temp = list.get(0).type
           while(cnt < list.size){
               if (list.get(cnt).type == temp ){
                   check = true
                   break
               }
           }
           if (check){
               result.add(temp.toString())
           }
           else{
               result.add("noMatch")
           }
       }
    }
}