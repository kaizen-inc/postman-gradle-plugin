package inc.kaizen.plugin.gradle

import com.github.gradle.node.task.NodeSetupTask
import inc.kaizen.plugin.gradle.extension.PostmanExtension
import inc.kaizen.plugin.gradle.task.RunPostmanCollectionTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class PostmanGradlePlugin : Plugin<Project> {

    companion object {
//        val MINIMAL_SUPPORTED_GRADLE_VERSION: GradleVersion = GradleVersion.version("6.6")
        const val PLUGIN_GROUP = "postman"
    }

    override fun apply(project: Project): Unit = project.run {
        pluginManager.apply("com.github.node-gradle.node")
        PostmanExtension.create(this)
        tasks.register(RunPostmanCollectionTask.NAME, RunPostmanCollectionTask::class.java) {
            it.dependsOn(listOf(NodeSetupTask.NAME))
        }
    }
}