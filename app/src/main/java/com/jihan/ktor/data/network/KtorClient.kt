package com.jihan.ktor.data.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


val httpClient = HttpClient(Android) {
    install(HttpTimeout) {
        requestTimeoutMillis = 15000
        socketTimeoutMillis = 15000
        connectTimeoutMillis = 15000
    }




    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.i("TAG", "log: $message")
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse {
            Log.d("TAG", "${it.status.value}: ")
        }
    }



    defaultRequest {
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
        header(
            HttpHeaders.Authorization,
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc3RAZ21haWwuY29tIiwidWlkIjoiYjMzOTMxNmU3MWVmNGQxMmFiNmY3NmRjYTNmODhiOTAiLCJpYXQiOjE3MzA2MjcyODZ9.aqHrey2LlaBf6kjMD9iUtwimyubj9PxzTlskGh6RDC8"
        )
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            isLenient = true
        })
    }


}


