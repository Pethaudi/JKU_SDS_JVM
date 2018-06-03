package entities

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

data class APPEARANCE(
        val id: Number,
        val degreeOfLatitude: Number,
        val degreeOfLongitude: Number,
        val date: String,
        val time: String,
        val terrain: String,
        val distanceToWater: Number,
        val city: String,
        val continent:String,
        val weather: String,
        val temperature: Number,
        val windvelocity: Number,
        val winddirection: Number,
        val airpressure: Number,
        val sunrise: String,
        val sunset: String,
        val minAfterSunrise: Number,
        val minPreSunrise: Number,
        val densityOfPopulation: Number,
        val gym: Number,
        val pokestop: Number
)

@Entity
@Table(schema = "IN110098", name = "POKEMON")
data class POKEMON(
        @Id
        val id: Number,
        val name: String,
        val height: Number,
        val weight: Number,
        val type: String,
        val category: String,
        val hp: Number,
        val attack: Number,
        val defense: Number,
        val sum: Number,
        val maxtournamentpoints: Number
) : Serializable