package inc.kaizen.plugin.gradle

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldNotBe
import org.gradle.testfixtures.ProjectBuilder

class PostmanGradlePluginFeatureSpec: FeatureSpec({
    feature("Postman Gradle Plugin") {
        scenario("Plugin can be applied to a project") {
            val project = ProjectBuilder.builder().build()
            project.pluginManager.apply("inc.kaizen.postman")

            project.plugins.findPlugin(PostmanGradlePlugin::class.java) shouldNotBe null
        }

        scenario("Plugin adds runPostmanCollection task to the project") {
            val project = ProjectBuilder.builder().build()
            project.pluginManager.apply("inc.kaizen.postman")

            project.tasks.findByName("runCollection") shouldNotBe null
        }
    }
})