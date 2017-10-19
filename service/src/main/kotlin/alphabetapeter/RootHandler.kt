package alphabetapeter

import io.netty.handler.codec.http.HttpHeaderNames
import io.netty.handler.codec.http.HttpHeaderValues
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class RootHandler : Handler<RoutingContext> {
    
    override fun handle(event: RoutingContext) {
        val response = event.response()
        println("Serving root")
        response
                .putHeader(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN)
                .end("Vert.x application running")
    }

}