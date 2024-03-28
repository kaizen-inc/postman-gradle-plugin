package inc.kaizen.plugin.gradle.task

import inc.kaizen.plugin.gradle.PostmanGradlePlugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.work.InputChanges

open class RunPostmanCollectionTask : DefaultTask() {

    companion object {
        const val NAME = "runPostmanCollection"
    }

    init {
        group = PostmanGradlePlugin.PLUGIN_GROUP
        outputs.upToDateWhen { true }
    }

    @TaskAction
    fun execute(inputs: InputChanges) { // InputChanges parameter

        val msg = if (inputs.isIncremental) "CHANGED inputs are out of date"
        else "ALL inputs are out of date"
        println(msg)
    }
}