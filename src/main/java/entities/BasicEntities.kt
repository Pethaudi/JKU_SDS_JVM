package entities

open class Counter(
        var counter: Int = 0
){
    open fun calcPercentage(sum: Int) = (counter / sum.toDouble()) * 100

    fun inc(){
        counter++
    }
}

data class AppearanceContinent(
        val continent: String
) : Counter()