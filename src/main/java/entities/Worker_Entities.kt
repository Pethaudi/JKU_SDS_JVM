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
        val continent: String,
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
class POKEMON() : Serializable {
    constructor(id: Number,
                name: String,
                height: Number,
                weight: Number,
                type: String,
                category: String,
                hp: Number,
                attack: Number,
                defense: Number,
                sum: Number,
                maxtournamentpoints: Number) : this() {

        this.id = id
        this.name = name
        this.height = height
        this.weight = weight
        this.type = type
        this.category = category
        this.hp = hp
        this.attack = attack
        this.defense = defense
        this.sum = sum
        this.maxtournamentpoints = maxtournamentpoints
    }

    @Id
    var id: Number? = null
    var name: String? = null
    var height: Number? = null
    var weight: Number? = null
    var type: String? = null
    var category: String? = null
    var hp: Number? = null
    var attack: Number? = null
    var defense: Number? = null
    var sum: Number? = null
    var maxtournamentpoints: Number? = null
}