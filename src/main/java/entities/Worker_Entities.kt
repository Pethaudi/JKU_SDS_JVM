package entities

import java.io.Serializable
import javax.persistence.*
import kotlin.math.min

@Entity
@Table(schema = "IN110098", name = "APPEARANCE")
class Appearance() : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Number? = null
    var pokemonid: Number? = null
    var degreeOfLatitude: Number? = null
    var degreeOfLongitude: Number? = null
    var date: String? = null
    var time: String? = null
    var terrain: String? = null
    var distanceToWater: Number? = null
    var city: String? = null
    var continent: String? = null
    var weather: String? = null
    var temperature: Number? = null
    var windvelocity: Number? = null
    var winddirection: Number? = null
    var airpressure: Number? = null
    var sunrise: String? = null
    var sunset: String? = null
    var minAfterSunrise: Number? = null
    var minPreSunrise: Number? = null
    var densityOfPopulation: Number? = null
    var gym: Number? = null
    var pokestop: Number? = null

    constructor(id: Number,
                degreeOfLatitude: Number,
                degreeOfLongitude: Number,
                date: String,
                time: String,
                terrain: String,
                distanceToWater: Number,
                city: String,
                continent: String,
                weather: String,
                temperature: Number,
                windvelocity: Number,
                winddirection: Number,
                airpressure: Number,
                sunrise: String,
                sunset: String,
                minAfterSunrise: Number,
                minPreSunrise: Number,
                densityOfPopulation: Number,
                gym: Number,
                pokestop: Number) : this(){

        this.pokemonid = id
        this.degreeOfLatitude = degreeOfLatitude
        this.degreeOfLongitude = degreeOfLongitude
        this.date = date
        this.time = time
        this.terrain = terrain
        this.distanceToWater = distanceToWater
        this.city = city
        this.continent = continent
        this.weather = weather
        this.temperature = temperature
        this.windvelocity = windvelocity
        this.winddirection = winddirection
        this.airpressure = airpressure
        this.sunrise = sunrise
        this.sunset = sunset
        this.minAfterSunrise = minAfterSunrise
        this.minPreSunrise = minPreSunrise
        this.densityOfPopulation = densityOfPopulation
        this.gym = gym
        this.pokestop = pokestop
    }
}

@Entity
@Table(schema = "IN110098", name = "POKEMON")
class Pokemon() : Serializable {
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