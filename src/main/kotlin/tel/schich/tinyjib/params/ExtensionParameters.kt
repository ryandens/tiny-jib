package tel.schich.tinyjib.params

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional

interface PluginExtensionsBuilder {
    fun extension(block: ExtensionParameters.() -> Unit)
}

internal class SimplePluginExtensionsBuilder(
    private val extensions: ListProperty<ExtensionParameters>,
    private val objectFactory: ObjectFactory,
) : PluginExtensionsBuilder {
    override fun extension(block: ExtensionParameters.() -> Unit) {
        val instance = objectFactory.newInstance(ExtensionParameters::class.java)
        instance.block()
        extensions.add(instance)
    }
}

abstract class ExtensionParameters {

    @get:Input
    abstract val extensionClass: Property<String>

    @get:Input
    @get:Optional
    abstract val properties: MapProperty<String, String>

    @get:Internal
    abstract val extraConfiguration: Property<Any>
}
