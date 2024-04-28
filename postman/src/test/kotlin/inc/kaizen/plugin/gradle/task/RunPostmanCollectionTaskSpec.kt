package inc.kaizen.plugin.gradle.task

import inc.kaizen.plugin.gradle.PostmanGradlePlugin
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder


class RunPostmanCollectionTaskSpec: FunSpec({

    test ("task name should be runCollection") {
        RunPostmanCollectionTask.NAME.shouldBe("runCollection")
    }

    test ("task group name should be postman") {
        val project: Project = ProjectBuilder.builder().build()
        project.plugins.apply(PostmanGradlePlugin::class.java)
        val task: Task = project.tasks.getByName("runCollection")
        task.shouldNotBeNull()
        task.shouldBeInstanceOf<RunPostmanCollectionTask>()
        task.group.shouldBe("postman")
    }

})