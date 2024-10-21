package io.github.jetqin

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable
data class Records(val id: Int, val firstName: String, val lastName: String)


fun Application.main() {
    val recordStorage = mutableListOf<Records>()
    recordStorage.addAll(
        arrayOf(
            Records(1, "Jane", "Smith"),
            Records(2, "John", "Smith")
        )
    )

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    routing {
        get("/records/{id}") {
            val id = call.parameters["id"]
            val record: Records = recordStorage.find { it.id == id!!.toInt() }!!
            call.respond(record)
        }

        post("/records") {
            val customer = call.receive<Records>()
            recordStorage.add(customer)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
    }
}