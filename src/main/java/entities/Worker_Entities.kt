package entities

import java.io.Serializable
import javax.persistence.*
import kotlin.math.min

@Entity
@Table(schema = "IN110098", name = "APPEARANCE")
data class Appearance(
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "AUTO_INC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "ID")
    val id: Number? = null,
    val pokemonid: Number? = null,
    val degreeOfLatitude: Number? = null,
    val degreeOfLongitude: Number? = null,
    val dateofspawn: String? = null,
    val time: String? = null,
    val terrain: String? = null,
    val distanceToWater: Number? = null,
    val city: String? = null,
    val continent: String? = null,
    val weather: String? = null,
    val temperature: Number? = null,
    val windvelocity: Number? = null,
    val winddirection: Number? = null,
    val airpressure: Number? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
    val minAfterSunrise: Number? = null,
    val minPreSunrise: Number? = null,
    val densityOfPopulation: Number? = null,
    val gym: Number? = null,
    val pokestop: Number? = null
    ) : Serializable {

    companion object {
        fun generateFromCsv(s: String): Appearance {
            val splitted = s.split(";")

            val pokestop = try {
                splitted[20].toDouble()
            } catch (ex: Exception){
                null
            }

            try {
                return Appearance(null,
                        splitted[0].toInt(),
                        splitted[1].tryToDouble(),
                        splitted[2].tryToDouble(),
                        splitted[3],
                        splitted[4],
                        splitted[5],
                        splitted[6].tryToDouble(),
                        splitted[7],
                        splitted[8],
                        splitted[9],
                        splitted[10].tryToDouble(),
                        splitted[11].tryToDouble(),
                        splitted[12].tryToDouble(),
                        splitted[13].tryToDouble(),
                        splitted[14],
                        splitted[15],
                        splitted[16].toInt(),
                        splitted[17].toInt(),
                        splitted[18].tryToDouble(),
                        splitted[19].tryToDouble(),
                        pokestop)
            } catch(ex: Exception){
                println(s)
                throw ex
            }
        }

        inline fun String.tryToDouble(): Double? {
            return try{
                this.toDouble()
            } catch(ex: Exception){
                null
            }
        }
    }
}

@Entity
@Table(schema = "IN110098", name = "POKEMON")
data class Pokemon(
    @Id
    val id: Number? = null,
    val name: String? = null,
    val height: Number? = null,
    val weight: Number? = null,
    val type: String? = null,
    val category: String? = null,
    val hp: Number? = null,
    val attack: Number? = null,
    val defense: Number? = null,
    val sum: Number? = null,
    val maxtp: Number? = null
    ) : Serializable {

    companion object {
        fun generateFromCsv(s: String): Pokemon{
            val splitted = s.split(";")

            return Pokemon(splitted[0].toInt(),
                    splitted[1],
                    splitted[2].toDouble(),
                    splitted[3].toDouble(),
                    splitted[4],
                    splitted[5],
                    splitted[6].toInt(),
                    splitted[7].toInt(),
                    splitted[8].toInt(),
                    splitted[9].toInt(),
                    splitted[10].toInt())
        }
    }
}