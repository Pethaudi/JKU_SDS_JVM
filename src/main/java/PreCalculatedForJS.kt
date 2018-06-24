import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

object PreCalculatedForJS {
    fun getGoogleMapsIconString(): String{
        val file = File("/Users/peterhauer/Desktop/ProgrammingStuff/DataScience/JKU_SDS_JVM/img")
        val icons = file.listFiles()
                .filter { it.extension != ".png" }
                .map { calcForOnePic(it) }
                .filter { it != null }
                .map { it!! }
                .toMutableList()

        sort(icons)

        return icons.map { it.toString() }.joinToString("\n")
    }

    fun getGoogleMapsIconSelector(): String {
        val beginning = "function getMarker(pokemonid){\n"

        var ifs = "    if(pokemonid == 1){\n" +
                "       return image001\n" +
                "   }\n"

        for(i in 2..151){
            val begin = "   else if(pokemonid == $i){\n"

            val image = if(i > 99) i.toString()
                else if(i > 9) "0$i"
                else "00$i"

            val inbetween = "       return image$image\n"

            val end = "    }\n"

            ifs += begin + inbetween + end
        }

        val end = "}"
        return beginning + ifs + end
    }

    private fun sort(list: MutableList<Icon>){
        for(elem1 in list.indices){
            for(elem2 in list.indices){
                if(list[elem2].name.toInt() > list[elem1].name.toInt()){
                    val tmp = list[elem1]
                    list[elem1] = list[elem2]
                    list[elem2] = tmp
                }
            }
        }
    }

    private fun calcForOnePic(pic: File): Icon?{
        val img = ImageIO.read(pic)
        if(img != null)
            return Icon(pic.nameWithoutExtension, img.width, img.height)
        return null
    }

    /*
    var icon = {
    url: "../res/sit_marron.png", // url
    scaledSize: new google.maps.Size(50, 50), // scaled size
    origin: new google.maps.Point(0,0), // origin
    anchor: new google.maps.Point(0, 0) // anchor
};
     */
    private class Icon(
            val name: String,
            var scaledSizeX: Int,
            var scaledSizeY: Int
    ) {
        override fun toString(): String {
            calcNewSize()
            val begin = "var image$name = {\n"
            val url = " url: \"src/img/$name.png\",\n"
            val scaledSize = "  scaledSize: new google.maps.Size($scaledSizeX, $scaledSizeY),\n"
            val origin = "  origin: new google.maps.Point(0, 0),\n"
            val anchor = "  anchor: new google.maps.Point(0, 0)\n"
            val end = "}\n"
            return begin + url + scaledSize + origin + anchor + end
        }

        private fun calcNewSize(){
            val n = if(scaledSizeX > scaledSizeY)
                    20.0 / scaledSizeX
                else
                    20.0 / scaledSizeY

            scaledSizeX = (n * scaledSizeX).roundToInt()
            scaledSizeY = (n * scaledSizeY).roundToInt()
        }
    }
}