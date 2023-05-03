package hotkitchen.presentation.routing.requestDto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable(with = CreateOrderRequest.Serializer::class)
class CreateOrderRequest(mealIds: List<Int>): List<Int> by mealIds {
    override fun toString(): String {
        return "CreateOrderRequest(${this.joinToString(", ")})"
    }

    object Serializer : KSerializer<CreateOrderRequest> {

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = SerialDescriptor("CreateOrderRequest", listSerialDescriptor<Int>())

        override fun deserialize(decoder: Decoder): CreateOrderRequest {
            val mealIds: List<Int> = decoder.decodeSerializableValue(ListSerializer(Int.serializer()))
            return CreateOrderRequest(mealIds)
        }

        override fun serialize(encoder: Encoder, value: CreateOrderRequest) {
            encoder.encodeSerializableValue(ListSerializer(Int.serializer()), value)
        }
    }
}

