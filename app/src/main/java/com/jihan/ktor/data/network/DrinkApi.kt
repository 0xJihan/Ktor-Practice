package com.jihan.ktor.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File

private const val BASE_URL =
    "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink"

class DrinkApi(private val client: HttpClient) {


    suspend fun getDrinks() = client.get(BASE_URL)


    suspend fun uploadImage(file: File): HttpResponse {
        return client.submitFormWithBinaryData(url = "https://run.mocky.io/v3/11358e1c-5c28-4eea-b7e0-76b6cd44adc0",
            formData {
                append(
                    "image",
                    file.readBytes(),
                    Headers.build {
                        append(
                            HttpHeaders.ContentDisposition,
                            "filename=${file.name}"
                        )
                    })
            }
            )

    }


}