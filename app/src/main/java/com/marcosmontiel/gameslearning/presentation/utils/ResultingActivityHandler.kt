package com.marcosmontiel.gameslearning.presentation.utils

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ResultingActivityHandler @Inject constructor() {
    private var _callback by mutableStateOf<(@Composable () -> Unit)?>(null)

    suspend fun takePicturePreview(maxAttempts: Int = 10, delayMillis: Long = 200): Bitmap? {
        return request(ActivityResultContracts.TakePicturePreview(), maxAttempts, delayMillis) {
            it.launch()
        }
    }

    suspend fun getContent(type: String, maxAttempts: Int = 10, delayMillis: Long = 200): Uri? {
        return request(ActivityResultContracts.GetContent(), maxAttempts, delayMillis) {
            it.launch(type)
        }
    }

    private suspend fun <I, O> request(
        contract: ActivityResultContract<I, O>,
        maxAttempts: Int = 10,
        delayMillis: Long = 200,
        launcher: (ActivityResultLauncher<I>) -> Unit
    ): O? = suspendCancellableCoroutine { coroutine ->
        _callback = {
            val a = rememberLauncherForActivityResult(contract) {
                coroutine.resume(value = it, onCancellation = {})
                _callback = null
                return@rememberLauncherForActivityResult
            }

            LaunchedEffect(a) {
                var tried = 0
                var tryOn = true
                while (tryOn) {
                    ++tried
                    delay(delayMillis)
                    try {
                        launcher(a)
                        tryOn = false
                    } catch (e: Exception) {
                        if (tried > maxAttempts) {
                            tryOn = false
                            coroutine.resume(value = null, onCancellation = {})
                            _callback = null
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Handle() {
        if (_callback != null) {
            _callback?.invoke()
        }
    }
}
