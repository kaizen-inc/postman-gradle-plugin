package inc.kaizen.plugin.gradle.task

import com.github.gradle.node.npm.task.NpmTask
import inc.kaizen.plugin.gradle.PostmanGradlePlugin
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class InstallNewmanTask : NpmTask() {

    companion object {
        const val NAME = "installNewman"
    }

    init {
        group = PostmanGradlePlugin.PLUGIN_GROUP
        description = "Install newman packages"
        npmCommand.set(listOf("install"))
        args.set(listOf("newman" + getNewmanVersionString()))
    }

    @OutputDirectory
    fun getNewmanOutputDir(): File {
        val newmanFolder = project.rootProject.file("node_modules/newman")
        return newmanFolder
    }

    @Input
    fun getNewmanVersionString(): String {
        // TODO: make version configurable
        return ""
    }

    @TaskAction
    fun execute() {
        println("Installing newman")
        super.exec()
    }
}