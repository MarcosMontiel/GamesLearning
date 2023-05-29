package com.marcosmontiel.gameslearning.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.marcosmontiel.gameslearning.R
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileOutputStream
import java.util.*

class ComposeFileProvider : FileProvider(R.xml.file_paths) {

    companion object {

        fun createFileFromUri(context: Context, uri: Uri): File? {
            val randomFileName: String = UUID.randomUUID().toString()
            val file: File = File.createTempFile(randomFileName, ".jpg", context.cacheDir)

            return try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    FileUtils.copyInputStreamToFile(inputStream, file)
                }
                file
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun createFileFromBitmap(context: Context, bitmap: Bitmap): File? {
            val randomFileName: String = UUID.randomUUID().toString()
            val file: File = File.createTempFile(randomFileName, ".jpg", context.cacheDir)

            return try {
                FileOutputStream(file).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }
                file
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    }

}
