package inc.kaizen.plugin.gradle.task

import inc.kaizen.plugin.gradle.PostmanGradlePlugin
import inc.kaizen.plugin.gradle.postman.NewmanRunner
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class RunPostmanCollectionTask: DefaultTask() {

    companion object {
        const val NAME = "runCollection"
    }

    init {
        group = PostmanGradlePlugin.PLUGIN_GROUP
        description = "Run postman collection using newman"
//        this.dependsOn(InstallNewmanTask.NAME)
//        outputs.upToDateWhen { true }
    }

    @TaskAction
    fun execute(/*inputs: InputChanges*/) {
        val newmanRunner = NewmanRunner()
        val result = newmanRunner.run(project)
        if (result.exitValue != 0) {
            throw Exception("Exception occurred while executing collection")
        }
    }
}
