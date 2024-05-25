package inc.kaizen.plugin.gradle

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import org.gradle.testfixtures.ProjectBuilder

class PostmanGradlePluginBehaviorSpec: BehaviorSpec({
    Given("a Gradle project") {
        val project = ProjectBuilder.builder().build()

        When("the Postman Gradle plugin is applied") {
            project.pluginManager.apply("io.kaizeninc.postman")

            Then("the plugin should be added to the project") {
                project.plugins.findPlugin(PostmanGradlePlugin::class.java) shouldNotBe null
            }

            Then("the runPostmanCollection task should be added to the project") {
                project.tasks.findByName("runCollection") shouldNotBe null
            }
        }
    }
})