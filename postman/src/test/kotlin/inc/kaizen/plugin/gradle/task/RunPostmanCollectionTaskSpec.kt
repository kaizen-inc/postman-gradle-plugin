package inc.kaizen.plugin.gradle.task

import inc.kaizen.plugin.gradle.PostmanGradlePlugin
import inc.kaizen.plugin.gradle.postman.NewmanRunner
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.process.ExecResult
import org.gradle.testfixtures.ProjectBuilder


class RunPostmanCollectionTaskSpec: FunSpec({

    val project: Project = mockk(relaxed = true)
    val newmanRunner: NewmanRunner = mockk(relaxed = true)
    val execResult: ExecResult = mockk(relaxed = true)

    every { newmanRunner.run(project) } returns execResult
    every { execResult.exitValue } returns 0



    test ("task name should be runCollection") {
        RunPostmanCollectionTask.NAME.shouldBe("runCollection")
    }

    xtest("execute should not throw exception when exit value is 0") {
        val runPostmanCollectionTask = RunPostmanCollectionTask()
        runPostmanCollectionTask.execute()
    }

    xtest("execute should throw exception when exit value is not 0") {
        val runPostmanCollectionTask = RunPostmanCollectionTask()
        every { execResult.exitValue } returns 1
        shouldThrow<Exception> { runPostmanCollectionTask.execute() }
    }

})