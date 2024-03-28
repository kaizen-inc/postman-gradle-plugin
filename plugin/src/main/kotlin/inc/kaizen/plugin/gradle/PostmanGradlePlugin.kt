package inc.kaizen.plugin.gradle

import inc.kaizen.plugin.gradle.extension.PostmanExtension
import inc.kaizen.plugin.gradle.task.RunPostmanCollectionTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.util.GradleVersion

class PostmanGradlePlugin: Plugin<Project> {

    companion object {
        val MINIMAL_SUPPORTED_GRADLE_VERSION: GradleVersion = GradleVersion.version("6.6")
        const val PLUGIN_GROUP = "postman"
    }

    override fun apply(project: Project) {
        val postmanExtension = PostmanExtension.create(project)
        project.tasks.register(RunPostmanCollectionTask.NAME, RunPostmanCollectionTask::class.java)
    }
}