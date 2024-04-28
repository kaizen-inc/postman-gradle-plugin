package inc.kaizen.plugin.gradle

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class ColorSpec : ShouldSpec({
    should("Enum should be of 3 types") {
        Color.entries.size.shouldBe(3)
    }

    should("check the types of color enums") {
        Color.entries.shouldContain(Color.AUTO)
        Color.entries.shouldContain(Color.ON)
        Color.entries.shouldContain(Color.OFF)
    }
})