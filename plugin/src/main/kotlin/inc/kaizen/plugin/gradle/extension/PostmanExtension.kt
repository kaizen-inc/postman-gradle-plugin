package inc.kaizen.plugin.gradle.extension

import com.google.gson.JsonObject
import inc.kaizen.plugin.gradle.Color
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Provider

open class PostmanExtension(project: Project) {

    private val collection: RegularFileProperty = project.objects.fileProperty()
    private val environment: RegularFileProperty = project.objects.fileProperty()
    private val globals: RegularFileProperty = project.objects.fileProperty()
    private val iterationData = project.objects.fileProperty()
    private val iterationCount = project.objects.property(Int::class.java).convention(1)
    private val folder = project.objects.fileTree()
    private val workingDir = project.objects.directoryProperty()
    private val noInsecureFileRead = project.objects.property(Boolean::class.java).convention(false)

    //    val exportEnvironment = project.objects.fileProperty()
//    val exportGlobals = project.objects.fileProperty()
//    val exportCollection  = project.objects.fileProperty()
    private val timeout = project.objects.property(Long::class.java)
    private val timeoutRequest = project.objects.property(Long::class.java)
    private val timeoutScript = project.objects.property(Long::class.java)
    private val insecure = project.objects.property(Boolean::class.java).convention(false)
    private val ignoreRedirects = project.objects.property(Boolean::class.java).convention(false)
    private val delayRequest = project.objects.property(Long::class.java)
    private val cookieJar = project.objects.fileProperty()

    //    val exportCookieJar = project.objects.fileProperty()
    private val bail = project.objects.property(Boolean::class.java).convention(false)
    private val suppressExitCode = project.objects.property(Boolean::class.java).convention(false)
    private val color = project.objects.property(Color::class.java).convention(Color.AUTO)

    //    val disableUnicode = project.objects.property(Boolean::class.java).convention(false)
    private val globalsVars = project.objects.mapProperty(String::class.java, String::class.java)
    private val envVars = project.objects.mapProperty(String::class.java, String::class.java)
    private val verbose = project.objects.property(Boolean::class.java).convention(false)

//    val cliReport = true
//    val xmlReportDir: String? = null
//    val htmlReportDir: String? = null
//    val htmlTemplate: String? = null
//    val jsonReportDir: String? = null


    fun toJson(): String {
        val jsonObject = jsonObject()

        with(jsonObject) {
            configure(this, "collection", collection)
            configure(this, "environment", environment)
            configure(this, "globals", globals)
            configure(this, "iterationData", iterationData)
            configure(this, "iterationCount", iterationCount)
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
        jsonObject.addProperty("folder", folder.dir.toString())
        return jsonObject.toString().replace("\"", "<>")
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