package entities

import java.sql.Date
import java.sql.Timestamp

data class APPEARANCE(
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
)