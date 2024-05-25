package inc.kaizen.plugin.gradle.postman

import inc.kaizen.plugin.gradle.extension.PostmanExtension
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.gradle.api.Project
import org.gradle.process.ExecResult
import java.io.File

class NewmanRunnerSpec: ShouldSpec ({

    val project: Project = mockk(relaxed = true)
    val postmanExtension: PostmanExtension = mockk(relaxed = true)
    val execResult: ExecResult = mockk(relaxed = true)
    val expectedFile: File = mockk(relaxed = true)

    every { project.extensions.getByType(PostmanExtension::class.java) } returns postmanExtension
    every { postmanExtension.ignoreExitValue() } returns false

    val newmanRunner = NewmanRunner()


    xshould("return ExecResult when run is called") {
        every { expectedFile.exists() } returns true
        every { project.rootProject.file(any()) } returns expectedFile
        every { project.rootProject.file("/.gradle/postman-runner/collection-runner.js") } returns expectedFile
        every { newmanRunner.getCollectionRunnerScriptFile(project) } returns expectedFile
        every { newmanRunner.run(project) } returns execResult

        //TODO: Fix this test to run actual code
        newmanRunner.run(project) shouldBe execResult
    }

    should("throw Exception when javascript file does not exist") {
        every { expectedFile.exists() } returns false
        every { project.rootProject.file(any()) } returns expectedFile
        shouldThrow<Exception> { newmanRunner.run(project) }
    }

    should("return File when JavaScript file exists") {
        every { expectedFile.exists() } returns true
        every { project.rootProject.file(any()) } returns expectedFile
        newmanRunner.getCollectionRunnerScriptFile(project) shouldBe expectedFile
    }

    should("throw RuntimeException when JavaScript file does not exist") {
        every { expectedFile.exists() } returns false
        every { project.rootProject.file(any()) } returns expectedFile

        val exception = shouldThrow<RuntimeException> { newmanRunner.getCollectionRunnerScriptFile(project) }
        exception.message shouldBe "Resource not found"
    }
})