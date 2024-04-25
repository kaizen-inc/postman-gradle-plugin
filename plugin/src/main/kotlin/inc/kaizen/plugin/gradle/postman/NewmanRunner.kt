package inc.kaizen.plugin.gradle.postman

import com.github.gradle.node.NodeExtension
import com.github.gradle.node.exec.NodeExecConfiguration
import com.github.gradle.node.exec.NodeExecRunner
import com.github.gradle.node.util.DefaultProjectApiHelper
import com.github.gradle.node.variant.VariantComputer
import inc.kaizen.plugin.gradle.extension.PostmanExtension
import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.process.ExecResult
import java.io.IOException

open class NewmanRunner() {
    private val nodeExecRunner = NodeExecRunner()

    fun run(project: Project): ExecResult {
        val postmanExtension: PostmanExtension = project.extensions.getByType(PostmanExtension::class.java)

        val projectApiHelper = project.objects.newInstance(DefaultProjectApiHelper::class.java)

        val extension = NodeExtension[project]

        val javascriptFile = project.rootProject.file("/.gradle/postman-runner/collection-runner.js")
        if (!javascriptFile.exists()) {
            try {
                val url = NewmanRunner::class.java.getResource("/script/collection-runner.js")
                FileUtils.copyURLToFile(url, javascriptFile)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        if (javascriptFile.exists()) {
            val command = listOf(javascriptFile.absolutePath, postmanExtension.toJson())
            val nodeExecConfiguration =
                NodeExecConfiguration(command,
                    emptyMap(),
                    project.projectDir,
                    postmanExtension.bail.get())

            return nodeExecRunner.execute(projectApiHelper, extension, nodeExecConfiguration, VariantComputer())
        } else {
            return project.exec {
                throw Exception("Resource not found")
            }
        }
    }
}
