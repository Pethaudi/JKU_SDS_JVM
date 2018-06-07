# PROJECT STRUCTURE
(everything marked with ! does not work)
## tablescript.sql
this is the script for the generation of the tables
## data/
here is the storage-folder for base-data

## results/
save all results here (attention: don't save files larger than 100MB)

## src/main/
### java/
#### dao/
create all workers/repos here
##### CsvWorker.kt
 - !insertPokemons()  
tries to insert every pokemon in the atlas db  
 - !insertAppearances()  
tries to insert every appearance in the atlas db  
 - getAllPokemons() : List<Worker_Entities.Pokemon>  
reads in all pokemons from the file  
 - getAllAppearances() : List<Worker_Entities.Appearances>  
reads in all appearances from the file  
 - measureReadInTime() : Int  
measures the time one complete read in takes  
##### !DbWorker.kt
i think you know  
problem: we don't have enough space
##### JsonWorker.kt
 - writeToFile(name: String, arr: JsonArray)  
writeToFile(name: String, obj: JsonObject)  
writeToFile(name: String, arr: List<JsonObject>)  
write your json data to a file  
just give it the name of the file (without extension)  
 - createFile(name: String)  
deletes and creates the file  
#### digger/
place here all classes that do the data digging
#### BasicSizes.kt
put here every basic size. what a basic size is?  
2 dimensional data

 - getAllContinents(): List<String>  
 - getAppearancesPerContinents(): List<NameCounter>  
 - getAppearancesPerContinentPercentage(): List<NamePercentage>  
 - getAllPokemonTypes(): List<String>  
 - getPokemon(id: Number): Pokemon  
 - getAppearancesPerTypes(): List<NameCounter>
#### entities/
save every entity here
##### sorted/
i will place here every extended entity for more object-oriented json
##### BasisEntities.kt
 - interface IJson  
 demands methodes for working with json  
toJson(obj)  
toObject(json)  

 - open class Counter  
 has the variable counter and the function calcPercentage(sum): Double
 - class NamePercentage  
 stores: name: String + percentage: Double
 - class NameCounter  
 stores: name: String + counter: Int
 
try to use the last 2 classes for easy data-storage
##### WorkerEntities.kt
this file contains every entity matching to the base-data
#### Program.kt
here is the main function
 - web_name: String  
 contains the absolut path to your web-project 
 - copyFilesToWeb(path) copies from data/ to your web-project
### resources/META-INF/
contains the persistence.xml

