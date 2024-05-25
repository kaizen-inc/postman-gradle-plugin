package inc.kaizen.plugin.gradle

import inc.kaizen.plugin.gradle.task.RunPostmanCollectionTask
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder

class PostmanGradlePluginSpec: FunSpec({

    test ("plugin should be applied") {
        val localProject: Project = ProjectBuilder.builder().build()
        localProject.plugins.apply(PostmanGradlePlugin::class.java)
        localProject.plugins.hasPlugin(PostmanGradlePlugin::class.java).shouldBe(true)
    }

    test ("task group name should be postman") {
        val localProject: Project = ProjectBuilder.builder().build()
        localProject.plugins.apply(PostmanGradlePlugin::class.java)
        val task: Task = localProject.tasks.getByName("runCollection")
        task.shouldNotBeNull()
        task.shouldBeInstanceOf<RunPostmanCollectionTask>()
        task.group.shouldBe("postman")
    }
})