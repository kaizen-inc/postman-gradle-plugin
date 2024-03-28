package inc.kaizen.plugin.gradle.extension

import inc.kaizen.plugin.gradle.Color
import org.gradle.api.Project

open class PostmanExtension(project: Project) {

    private val environment = project.objects.fileProperty()
    private val globals = project.objects.fileProperty()
    private val iterationData = project.objects.fileProperty()
    private val iterationCount = project.objects.property(Int::class.java)
    private val folder = project.objects.fileTree()
    private val workingDir = project.objects.directoryProperty()
    private val noInsecureFileRead = project.objects.property(Boolean::class.java).convention(false)
    private val exportEnvironment = project.objects.fileProperty()
    private val exportGlobals = project.objects.fileProperty()
    private val exportCollection  = project.objects.fileProperty()
    private val timeout  = project.objects.property(Long::class.java)
    private val timeoutRequest  = project.objects.property(Long::class.java)
    private val timeoutScript  = project.objects.property(Long::class.java)
    private val insecure = project.objects.property(Boolean::class.java).convention(false)
    private val ignoreRedirects = project.objects.property(Boolean::class.java).convention(false)
    private val delayRequest = project.objects.property(Long::class.java)
    private val cookieJar = project.objects.fileProperty()
    private val exportCookieJar = project.objects.fileProperty()
    private val bail = project.objects.property(Boolean::class.java).convention(false)
    private val suppressExitCode = project.objects.property(Boolean::class.java).convention(false)
    private val color = project.objects.property(Color::class.java).convention(Color.AUTO)
    private val disableUnicode = project.objects.property(Boolean::class.java).convention(false)
    private val globalsVars = project.objects.mapProperty(String::class.java, String::class.java)
    private val envVars = project.objects.mapProperty(String::class.java, String::class.java)
    private val verbose = project.objects.property(Boolean::class.java).convention(false)

//    private val cliReport = true
//    private val xmlReportDir: String? = null
//    private val htmlReportDir: String? = null
//    private val htmlTemplate: String? = null
//    private val jsonReportDir: String? = null

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