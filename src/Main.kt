package com.manqana

import com.google.gson.Gson
import com.manqana.model.entities.Car
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.lang.Exception


const val targetUrl: String = "https://copart.com"
fun main(args: Array<String>) {
    val car = Car(model = "Audi A4 B8", price = 5800.0)
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                val result = try {
                    fetchContent(targetUrl)
                } catch (e: Exception) {
                    e.message ?: "error"
                }
                call.respond(result ?: "No result")
            }
        }
    }
    server.start(wait = true)
}

private val client: HttpClient by lazy { HttpClient() }

private suspend fun fetchContent(targetUrl: String): String? {
    val result = client.get<String>(targetUrl)
    return result
}