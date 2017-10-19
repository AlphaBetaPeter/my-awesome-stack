package alphabetapeter.verticle

import alphabetapeter.handler.RootHandler
import alphabetapeter.mongo.GetPetsHandler
import alphabetapeter.mongo.SavePetHandler
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler


class ServiceVerticle(private val mongoClient: MongoClient) : AbstractVerticle() {

	override fun start(startFuture: Future<Void>) {
		val router = Router.router(vertx)

		// Log requests
		router.route().handler({
			println("Requested ${it.request().absoluteURI()}")
			it.next()
		})
		router.route().handler(BodyHandler.create())

		router.get("/").handler(RootHandler())
		router.put("/pet").handler(SavePetHandler(mongoClient))
		router.get("/pets").handler(GetPetsHandler(mongoClient))

		val servicePort = config().getInteger("http.port", 8080)

		startHttpServer(router, servicePort, startFuture)
	}

	private fun startHttpServer(router: Router, servicePort: Int, startFuture: Future<Void>) {
		vertx
				.createHttpServer()
				.requestHandler(router::accept)
				.listen(servicePort) { asyncResult ->
					if (asyncResult.succeeded()) {
						println("Server listening on port $servicePort")
						startFuture.complete()
					} else {
						println("Failed to create http server")
						startFuture.fail(asyncResult.cause())
					}
				}
	}
}