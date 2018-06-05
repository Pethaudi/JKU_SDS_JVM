package dao

import java.io.File
import javax.json.Json
import javax.json.JsonArray
import javax.json.JsonObject

class JsonWorker {
    companion object {
        fun writeToFile(name: String, arr: JsonArray) {
            val file = createFile(name)
            file.writeText(arr.toString())
        }

        fun writeToFile(name: String, obj: JsonObject) {
            val file = createFile(name)
            file.writeText(obj.toString())
        }

        fun writeToFile(name: String, arr: List<JsonObject>) {
            val builder = Json.createArrayBuilder()
            arr.forEach { builder.add(it) }
            writeToFile(name, builder.build())
        }

        private fun createFile(name: String): File {
            val file = File("results/$name.json")
            if (file.exists())
                file.delete()
            file.createNewFile()
            return file
        }
    }
}