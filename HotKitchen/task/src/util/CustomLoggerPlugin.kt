package hotkitchen.util

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.util.*

class CustomLoggerPlugin {

    class Configuration

    companion object Plugin : ApplicationFeature<ApplicationCallPipeline, Configuration, CustomLoggerPlugin> {
        override val key = AttributeKey<CustomLoggerPlugin>("CustomLoggerPlugin")
        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): CustomLoggerPlugin {
            val plugin = CustomLoggerPlugin()

            pipeline.intercept(ApplicationCallPipeline.Monitoring) { data ->
                println("call: ${context.request.toLogString()}")
                proceedWith(subject)
            }

            pipeline.sendPipeline.intercept(ApplicationSendPipeline.Transform) { data ->
                val dataString = if(data is Unit) "" else ", $data"
                println("response: ${call.response.status() ?: ""}$dataString")
                proceedWith(subject)
            }

            return plugin
        }
    }
}