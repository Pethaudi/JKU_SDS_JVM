package entities.sorted

import entities.Coordinate
import entities.IJson
import javax.json.Json
import javax.json.JsonObject

class AppearancesPerDayWithCoordinates(
        val day: String,
        val appearances: List<Coordinate>
) {
    companion object : IJson {
        override fun toJson(obj: Any): JsonObject {
            val builder = Json.createObjectBuilder()
            val arr = Json.createArrayBuilder()
            if(obj is AppearancesPerDayWithCoordinates) {
                obj.appearances.forEach {
                    arr.add(Coordinate.toJson(it))
                }

                builder.add("appearances", arr.build())
                        .add("day", obj.day)

                return builder.build()
            }

            throw IllegalArgumentException()
        }

        override fun toObject(json: JsonObject): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}