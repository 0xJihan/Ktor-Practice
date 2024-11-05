package com.jihan.ktor.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihan.ktor.data.repository.DrinkRepository
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {

    val drinksResponse = drinkRepository.drinksResponse


    val uploadImageResponse = drinkRepository.uploadImageResponse


    fun uploadImage(file: File) {
        viewModelScope.launch {
            drinkRepository.uploadImage(file)
        }
    }


}