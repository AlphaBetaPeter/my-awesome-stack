package alphabetapeter.mongo

import io.netty.handler.codec.http.HttpHeaderNames
import io.netty.handler.codec.http.HttpHeaderValues
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.RoutingContext

class GetPetsHandler(private val mongoClient: MongoClient) : Handler<RoutingContext> {

    override fun handle(event: RoutingContext) {

        val response = event.response()

        println("Reading pets")
        mongoClient.find("pets", JsonObject()) { res ->
            if (res.succeeded()) {
                response
                        .putHeader(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                        .end(JsonObject().put("pets", res.result()).encode())

            } else {
                res.cause().printStackTrace()
                response
                        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                        .end(res.cause().message)
            }

        }

    }

}