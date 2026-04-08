package tel.schich.tinyjib.params

import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

abstract class ExtensionParameters {

    @get:Input
    abstract val extensionClass: Property<String>

    @get:Input
    @get:Optional
    abstract val properties: MapProperty<String, String>

    @get:Input
    @get:Optional
    abstract val extraConfiguration: Property<Any>
}
