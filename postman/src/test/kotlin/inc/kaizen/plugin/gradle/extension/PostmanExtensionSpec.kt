package inc.kaizen.plugin.gradle.extension

import inc.kaizen.plugin.gradle.Color
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class PostmanExtensionSpec: ShouldSpec ({

    val project: Project = ProjectBuilder.builder().build()

    should("return bail value when ignoreExitValue is called") {
        val postmanExtension = PostmanExtension(project)
        postmanExtension.bail.set(true)
        postmanExtension.ignoreExitValue() shouldBe true
    }

    should("return false when ignoreExitValue is called and bail is not set") {
        val postmanExtension = PostmanExtension(project)
        postmanExtension.ignoreExitValue() shouldBe false
    }

    should("return json string when toJson is called") {
        val postmanExtension = PostmanExtension(project)
        postmanExtension.collection.set(project.file("collection"))
        postmanExtension.environment.set(project.file("environment"))
        postmanExtension.globals.set(project.file("globals"))
        postmanExtension.iterationData.set(project.file("iterationData"))
        postmanExtension.iterationCount.set(1)
        postmanExtension.folder.set("folder")
        postmanExtension.workingDir.set(project.layout.projectDirectory)
        postmanExtension.noInsecureFileRead.set(false)
        postmanExtension.timeout.set(1000L)
        postmanExtension.timeoutRequest.set(1000L)
        postmanExtension.timeoutScript.set(1000L)
        postmanExtension.insecure.set(false)
        postmanExtension.ignoreRedirects.set(false)
        postmanExtension.delayRequest.set(1000L)
        postmanExtension.cookieJar.set(project.file("cookieJar"))
        postmanExtension.bail.set(false)
        postmanExtension.suppressExitCode.set(false)
        postmanExtension.color.set(Color.AUTO)
        postmanExtension.globalsVars.put("key", "value")
        postmanExtension.envVars.put("key", "value")
        postmanExtension.verbose.set(false)

        val jsonString = postmanExtension.toJson()
        jsonString.contains("collection") shouldBe true
        jsonString.contains("environment") shouldBe true
        jsonString.contains("globals") shouldBe true
        jsonString.contains("iterationData") shouldBe true
        jsonString.contains("iterationCount") shouldBe true
        jsonString.contains("folder") shouldBe true
        jsonString.contains("workingDir") shouldBe true
        jsonString.contains("noInsecureFileRead") shouldBe true
        jsonString.contains("timeout") shouldBe true
        jsonString.contains("timeoutRequest") shouldBe true
        jsonString.contains("timeoutScript") shouldBe true
        jsonString.contains("insecure") shouldBe true
        jsonString.contains("ignoreRedirects") shouldBe true
        jsonString.contains("delayRequest") shouldBe true
        jsonString.contains("cookieJar") shouldBe true
        jsonString.contains("bail") shouldBe true
        jsonString.contains("suppressExitCode") shouldBe true
        jsonString.contains("color") shouldBe true
        jsonString.contains("globalsVars") shouldBe true
        jsonString.contains("envVars") shouldBe true
        jsonString.contains("verbose") shouldBe true
    }
})