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

    companion object {
        fun sum(data: List<Counter>) = data.sumBy { it.counter }
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
                return NamePercentage(json["name"]!!.toString(), json["percentage"]!!.toString().toDouble())
            } catch(ex: Exception){
                throw IllegalArgumentException()
            }
        }
    }
}

class NameCounter(
        val name: String,
        counter: Int = 0
) : Counter(counter) {
    companion object : IJson {
        override fun toJson(obj: Any): JsonObject {

            if(obj is NameCounter) {
                val builder = Json.createObjectBuilder()

                return builder.add("name", obj.name)
                        .add("counter", obj.counter)
                        .build()
            }
            throw IllegalArgumentException()
        }

        override fun toObject(json: JsonObject): Any {
            try{
                return NameCounter(json["name"]!!.toString(), json["counter"]!!.toString().toInt())
            } catch(ex: Exception){
                throw IllegalArgumentException()
            }
        }
    }

    override fun toString(): String {
        return "$name + $counter"
    }
}

class AppearancesPerDayWithLatLong(
        val pokemonid: Number,
        val day: String,
        val long: Number,
        val lat: Number
){
    companion object : IJson {
        override fun toJson(obj: Any): JsonObject {

            if(obj is AppearancesPerDayWithLatLong) {
                val builder = Json.createObjectBuilder()

                return builder.add("pokemon", obj.pokemonid.toInt())
                        .add("day", obj.day)
                        .add("long", obj.long.toInt())
                        .add("lat", obj.lat.toInt())
                        .build()
            }
            throw IllegalArgumentException()
        }

        override fun toObject(json: JsonObject): Any {
            try{
                return NameCounter(json["name"]!!.toString(), json["counter"]!!.toString().toInt())
            } catch(ex: Exception){
                throw IllegalArgumentException()
            }
        }
    }
}