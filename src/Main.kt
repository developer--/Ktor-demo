package com.manqana

import com.google.gson.Gson
import com.manqana.data.network.NetworkHandler
import com.manqana.model.entities.Car
import com.manqana.model.parser.CopartFileParser
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.ConstantCookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.client.features.cookies.cookies
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.http.Cookie
import io.ktor.http.Parameters
import io.ktor.http.cookies
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.lang.Exception


const val targetUrl: String = "https://www.copart.com/public/vehicleFinder/search"
fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                val result = try {
                    sendRequest(targetUrl)
                } catch (e: Exception) {
                    e.message ?: "error"
                }
//                val result =CopartFileParser.parse("../../server/manqana/copart.txt")
                call.respond(result ?: "No result")
            }
        }
    }
    server.start(wait = true)
}

private val client: HttpClient by lazy {
    HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }
}

private suspend fun fetchContent(targetUrl: String): String? {
    val result = client.get<String>(targetUrl)
    return result
}

private suspend fun sendRequest(targetUrl: String): String? {
    val result = NetworkHandler.makeRequest(targetUrl)
    return result.toString()
}