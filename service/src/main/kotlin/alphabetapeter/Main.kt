package alphabetapeter

import io.vertx.core.Vertx

fun main(args: Array<String>) {
	println("Server starting")
	val vertx = Vertx.vertx()
	vertx.deployVerticle(ServiceVerticle(), { res ->
		if (res.succeeded()) {
			println("Deployment succeeded. Id is: ${res.result()}")
		} else {
			println("Deployment failed! ${res.cause()}")
		}
	})
}

