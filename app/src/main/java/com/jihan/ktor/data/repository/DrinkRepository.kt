package com.jihan.ktor.data.repository

import com.jihan.ktor.data.model.DrinksResponse
import com.jihan.ktor.data.network.DrinkApi
import com.jihan.ktor.utils.UiState
import io.ktor.client.call.body
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File


class DrinkRepository(private val drinkApi: DrinkApi) {

    private var _drinksResponse = MutableStateFlow<UiState<DrinksResponse>>(UiState.Initial())
    val drinksResponse = _drinksResponse.asStateFlow()

    private var _uploadImageResponse = MutableStateFlow<UiState<String>>(UiState.Initial())
    val uploadImageResponse = _uploadImageResponse.asStateFlow()

    suspend fun uploadImage(file: File) {
        _uploadImageResponse.emit(UiState.Loading())

        val result = kotlin.runCatching { drinkApi.uploadImage(file) }

        result.fold(onSuccess = {
            val text = "File uploaded successfully"
            _uploadImageResponse.emit(UiState.Success(text))
        }, onFailure = {
            _uploadImageResponse.emit(UiState.Error(it.localizedMessage))
        })

    }


    suspend fun getDrinks() {
        _drinksResponse.emit(UiState.Loading())

        val result = kotlin.runCatching { drinkApi.getDrinks().body<DrinksResponse>() }

        result.fold(onSuccess = {
            _drinksResponse.emit(UiState.Success(it))
        }, onFailure = {
            _drinksResponse.emit(UiState.Error(it.localizedMessage))
        })

    }

}