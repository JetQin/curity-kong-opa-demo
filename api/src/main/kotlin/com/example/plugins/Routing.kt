package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

// Sample data class (replace with your actual data model)
data class Record(val id: Int, val name: String, val value: String)

// Sample list of records (replace with your actual data source)
val records = listOf(
    Record(1, "Record 1", "Data 1"),
    Record(2, "Record 2", "Data 2"),
    Record(3, "Record 3", "Data 3")
)

fun Application.configureRouting() {
    routing {
        get("/health") {
            call.respondText("Ready!")
        }

        // API to list all records
        get("/api/records") {
            call.respond(records)
        }

        // API to get a specific record by ID
        get("/api/records/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id != null) {
                val record = records.find { it.id == id }
                if (record != null) {
                    call.respond(record)
                } else {
                    call.respondText("Record not found", status = HttpStatusCode.NotFound)
                }
            } else {
                call.respondText("Invalid ID", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
