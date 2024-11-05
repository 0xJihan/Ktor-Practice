package com.jihan.ktor.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

fun Uri.toFile(context: Context): File {

    val filesDir = context.filesDir //? private files directory of the app

    val file = File(filesDir, "image.jpg")

    context.contentResolver.openInputStream(this)?.use { inputStream ->
        FileOutputStream(file).use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }


    return file
}
