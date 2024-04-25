package inc.kaizen.plugin.gradle.extension

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive

fun empty(reporter: JsonObject): Boolean {
    return reporter.entrySet().size == 0
}

fun empty(reporters: JsonArray): Boolean {
    return reporters.size() == 0
}

fun jsonObject(): JsonObject {
    return JsonObject()
}

fun jsonObject(key: String?, value: String?): JsonObject {
    val jsonObject: JsonObject = jsonObject()
    jsonObject.addProperty(key, value)
    return jsonObject
}

fun primitive(string: String?): JsonPrimitive {
    return JsonPrimitive(string)
}

fun primitive(bool: Boolean): JsonPrimitive {
    return JsonPrimitive(bool)
}

fun array(): JsonArray {
    return JsonArray()
}