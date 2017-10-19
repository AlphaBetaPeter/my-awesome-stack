package alphabetapeter

import io.netty.handler.codec.http.HttpHeaderNames
import io.netty.handler.codec.http.HttpHeaderValues
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import java.util.*

class PetHandler : Handler<RoutingContext> {

    override fun handle(event: RoutingContext) {

        val response = event.response()
        val pet = buildPet()
        println("Serving pet $pet")
        response
                .putHeader(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .end(pet)
    }

    private fun buildPet() = JsonObject()
            .put("name", getPetName())
            .put("type", getPetType())
            .put("age", Random().nextInt(10))
            .encode()

    private fun getPetType(): String {
        val types = listOf("mouse", "cat", "horse")
        return types[Random().nextInt(types.size)]
    }

    private fun getPetName(): String {
        val names = listOf("fred", "greg", "paul")
        return names[Random().nextInt(names.size)]
    }

}