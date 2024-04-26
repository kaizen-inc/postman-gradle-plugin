package inc.kaizen.plugin.gradle.extension

import com.google.gson.JsonObject
import inc.kaizen.plugin.gradle.Color
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Provider

open class PostmanExtension(project: Project) {

    val collection: RegularFileProperty = project.objects.fileProperty()
    val environment: RegularFileProperty = project.objects.fileProperty()
    val globals: RegularFileProperty = project.objects.fileProperty()
    val iterationData = project.objects.fileProperty()
    val iterationCount = project.objects.property(Int::class.java).convention(1)
    val folder = project.objects.property(String::class.java)
    val workingDir = project.objects.directoryProperty()
    val noInsecureFileRead = project.objects.property(Boolean::class.java).convention(false)

    //    val exportEnvironment = project.objects.fileProperty()
//    val exportGlobals = project.objects.fileProperty()
//    val exportCollection  = project.objects.fileProperty()
    val timeout = project.objects.property(Long::class.java)
    val timeoutRequest = project.objects.property(Long::class.java)
    val timeoutScript = project.objects.property(Long::class.java)
    val insecure = project.objects.property(Boolean::class.java).convention(false)
    val ignoreRedirects = project.objects.property(Boolean::class.java).convention(false)
    val delayRequest = project.objects.property(Long::class.java)
    val cookieJar = project.objects.fileProperty()

    //    val exportCookieJar = project.objects.fileProperty()
    val bail = project.objects.property(Boolean::class.java).convention(false)
    val suppressExitCode = project.objects.property(Boolean::class.java).convention(false)
    val color = project.objects.property(Color::class.java).convention(Color.AUTO)

    //    val disableUnicode = project.objects.property(Boolean::class.java).convention(false)
    val globalsVars = project.objects.mapProperty(String::class.java, String::class.java)
    val envVars = project.objects.mapProperty(String::class.java, String::class.java)
    val verbose = project.objects.property(Boolean::class.java).convention(false)

//    val cliReport = true
//    val xmlReportDir: String? = null
//    val htmlReportDir: String? = null
//    val htmlTemplate: String? = null
//    val jsonReportDir: String? = null


    fun toJson(): String {
        try {
            val jsonObject = jsonObject()

            with(jsonObject) {
                configure(this, "collection", collection)
                configure(this, "environment", environment)
                configure(this, "globals", globals)
                configure(this, "iterationData", iterationData)
                configure(this, "iterationCount", iterationCount)
                configure(this, "folder", folder)
                configure(this, "workingDir", workingDir)
                configure(this, "noInsecureFileRead", noInsecureFileRead)
                configure(this, "timeout", timeout)
                configure(this, "timeoutRequest", timeoutRequest)
                configure(this, "timeoutScript", timeoutScript)
                configure(this, "insecure", insecure)
                configure(this, "ignoreRedirects", ignoreRedirects)
                configure(this, "delayRequest", delayRequest)
                configure(this, "cookieJar", cookieJar)
                configure(this, "bail", bail)
                configure(this, "suppressExitCode", suppressExitCode)
                configure(this, "color", color)
                configure(this, "globalsVars", globalsVars)
                configure(this, "envVars", envVars)
                configure(this, "verbose", verbose)
            }
            return jsonObject.toString().replace("\"", "<>")
        } catch (e: Exception) {
            throw Exception("Failed to create json from gradle configuration")
        }
    }

    private fun configure(
        jsonObject: JsonObject,
        propertyName: String,
        property: Provider<out Any>
    ) {
        if (property.isPresent) {
            jsonObject.addProperty(propertyName, property.get().toString())
        }
    }

    fun ignoreExitValue(): Boolean {
        return bail.get()
    }

    companion object {
        private const val NAME = "postman"

        @JvmStatic
        operator fun get(project: Project): PostmanExtension {
            return project.extensions.getByType(PostmanExtension::class.java)
        }

        @JvmStatic
        fun create(project: Project): PostmanExtension {
            return project.extensions.create(NAME, PostmanExtension::class.java, project)
        }
    }
}