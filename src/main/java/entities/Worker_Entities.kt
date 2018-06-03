package entities

import java.sql.Date
import java.sql.Timestamp

data class JKU_SDS_Entry(
        val id: Number,
        val degreeOfLatitude: Number,
        val degreeOfLongitude: Number,
        val date: Date,
        val time: Timestamp,
        val terrain: String,
        val distanceToWater: Number,
        val city: String,
        val continent:String,
        val weather: String,
        val temperature: Number,
        val windvelocity: Number,
        val winddirection: Number,
        val airpressure: Number,
        val sunrise: Timestamp,
        val sunset: Timestamp,
        val minAfterSunrise: Number,
        val minPreSunrise: Number,
        val densityOfPopulation: Number,
        val gym: Number,
        val pokestop: Number
) {
    companion object {
        fun generateSqlCommand(data: String): String{
            val splitted = data.split(";")
            var command = "insert into JKU_SDS values(" +
                    "${splitted[0]}," +
                    "${splitted[1]}," +
                    "${splitted[2]}," +
                    "\"${splitted[3]}\"," +
                    "\"${splitted[4]}\"," +
                    "\"${splitted[5]}\"," +
                    "${splitted[6]}," +
                    "\"${splitted[7]}\"," +
                    "\"${splitted[8]}\"," +
                    "\"${splitted[9]}\"," +
                    "${splitted[10]}," +
                    "${splitted[11]}," +
                    "${splitted[12]}," +
                    "${splitted[13]}," +
                    "\"${splitted[14]}\"," +
                    "\"${splitted[15]}\"," +
                    "${splitted[16]}," +
                    "${splitted[17]}," +
                    "${splitted[18]}," +
                    "${splitted[19]}," +
                    "${splitted[20]}," +
                    ");"

            return command
        }
    }
}