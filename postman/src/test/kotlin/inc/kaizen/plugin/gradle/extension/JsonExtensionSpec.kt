package inc.kaizen.plugin.gradle.extension

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class JsonExtensionSpec: ShouldSpec ({

    should("return true when JsonObject is empty") {
        val jsonObject = JsonObject()
        empty(jsonObject) shouldBe true
    }

    should("return false when JsonObject is not empty") {
        val jsonObject = JsonObject()
        jsonObject.addProperty("key", "value")
        empty(jsonObject) shouldBe false
    }

    should("return true when JsonArray is empty") {
        val jsonArray = JsonArray()
        empty(jsonArray) shouldBe true
    }

    should("return false when JsonArray is not empty") {
        val jsonArray = JsonArray()
        jsonArray.add("value")
        empty(jsonArray) shouldBe false
    }

    should("return JsonObject with given key-value pair") {
        val key = "key"
        val value = "value"
        val jsonObject = jsonObject(key, value)
        jsonObject.get(key).asString shouldBe value
    }

    should("return JsonPrimitive with given string") {
        val value = "value"
        val jsonPrimitive = primitive(value)
        jsonPrimitive.asString shouldBe value
    }

    should("return JsonPrimitive with given boolean") {
        val value = true
        val jsonPrimitive = primitive(value)
        jsonPrimitive.asBoolean shouldBe value
    }

    should("return an empty JsonArray") {
        val jsonArray = array()
        jsonArray.isJsonArray shouldBe true
        jsonArray.size() shouldBe 0
    }
})