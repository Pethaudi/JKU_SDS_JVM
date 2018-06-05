package entities

import javax.json.Json
import javax.json.JsonObject
import javax.json.JsonObjectBuilder

/*
every object should import this interface
 */
interface IJson{
    fun toJson(obj: Any): JsonObject
    fun toObject(json: JsonObject): Any
}

/*
this needed for some calcs (mostly percentage)
 */
open class Counter(
        var counter: Int = 0
){
    open fun calcPercentage(sum: Int) = (counter / sum.toDouble()) * 100

    fun inc(){
        counter++
    }
}

/*
self-explaining
 */
class AppearanceContinent(
        val continent: String,
        counter: Int = 0
) : Counter(counter) {

    /*
    cause static
     */
    companion object : IJson {

        override fun toJson(obj: Any): JsonObject {

            if(obj is AppearanceContinent) {
                val builder = Json.createObjectBuilder()

                return builder.add("continent", obj.continent)
                        .add("counter", obj.counter)
                        .build()
            }
            throw IllegalArgumentException()
        }

        override fun toObject(json: JsonObject): Any {
            try{
                return AppearanceContinent(json["continent"]!!.toString(), json["counter"]!!.toString().toInt())
            } catch(ex: Exception){
                throw IllegalArgumentException()
            }
        }
    }
}

class NamePercentage(
        val name: String,
        val percentage: Double
) {
    companion object : IJson {
        override fun toJson(obj: Any): JsonObject {

            if(obj is NamePercentage) {
                val builder = Json.createObjectBuilder()

                return builder.add("name", obj.name)
                        .add("percentage", obj.percentage)
                        .build()
            }
            throw IllegalArgumentException()
        }

        override fun toObject(json: JsonObject): Any {
            try{
                return AppearanceContinent(json["name"]!!.toString(), json["percentage"]!!.toString().toInt())
            } catch(ex: Exception){
                throw IllegalArgumentException()
            }
        }
    }
}