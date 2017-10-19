package alphabetapeter

import alphabetapeter.verticle.ServiceVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient

fun main(args: Array<String>) {
	println("Server starting")
	val vertx = Vertx.vertx()
	val mongoConfig = JsonObject()
			.put("host", "my-awesome-database")
			.put("port", 27017)
	val mongoClient = MongoClient.createShared(vertx, mongoConfig)

	vertx.deployVerticle(ServiceVerticle(mongoClient), { res ->
		if (res.succeeded()) {
			println("Deployment succeeded. Id is: ${res.result()}")
		} else {
			println("Deployment failed! ${res.cause()}")
		}
	})
}

