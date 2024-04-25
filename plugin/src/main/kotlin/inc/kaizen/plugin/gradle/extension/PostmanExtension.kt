package inc.kaizen.plugin.gradle.extension

import inc.kaizen.plugin.gradle.Color
import org.gradle.api.Project

open class PostmanExtension(project: Project) {

    val environment = project.objects.fileProperty()
    val globals = project.objects.fileProperty()
    val iterationData = project.objects.fileProperty()
    val iterationCount = project.objects.property(Int::class.java)
    val folder = project.objects.fileTree()
    val workingDir = project.objects.directoryProperty()
    val noInsecureFileRead = project.objects.property(Boolean::class.java).convention(false)
//    val exportEnvironment = project.objects.fileProperty()
//    val exportGlobals = project.objects.fileProperty()
//    val exportCollection  = project.objects.fileProperty()
    val timeout  = project.objects.property(Long::class.java)
    val timeoutRequest  = project.objects.property(Long::class.java)
    val timeoutScript  = project.objects.property(Long::class.java)
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

        val jsonObject = jsonObject()
        jsonObject.addProperty("collection", "./src/test/resources/sample-collection.json")
        return jsonObject.toString().replace("\"", "<>")
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