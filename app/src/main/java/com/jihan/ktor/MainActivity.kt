package com.jihan.ktor

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jihan.ktor.data.viewmodel.MainViewModel
import com.jihan.ktor.ui.theme.AppTheme
import com.jihan.ktor.ui.theme.components.NetworkImage
import com.jihan.ktor.utils.UiState
import com.jihan.ktor.utils.toFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {

    private var isReady: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setKeepOnScreenCondition {
                !isReady
            }

            splashScreen.setOnExitAnimationListener { screen ->

                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.4f, 0.0f
                ).setDuration(500L)
                zoomX.interpolator = OvershootInterpolator()
                zoomX.doOnEnd { screen.remove() }
                zoomX.start()

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView, View.SCALE_X, 0.4f, 0.0f
                ).setDuration(500L)
                zoomY.interpolator = OvershootInterpolator()
                zoomY.doOnEnd { screen.remove() }
                zoomY.start()

            }

            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                isReady = true
            } // delay the splash screen
        } // showing the animated splash screen for android 12

        setContent {
            AppTheme {
                MainApp()
            }
        }
    }

}


@Composable
fun MainApp() {





    val context = LocalContext.current
    val viewmodel : MainViewModel = koinInject()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    val response by viewmodel.uploadImageResponse.collectAsStateWithLifecycle()


    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        NetworkImage(Modifier.size(100.dp), imageUri)
        Spacer(Modifier.height(10.dp))


        when (response){
            is UiState.Error -> {
                Text(text = response.message.toString())
            }
            is UiState.Initial -> {}
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                Text(text = response.data.toString())
            }
        }


        Button(onClick = {
            launcher.launch("image/*")
        }) { Text("Select Image") }

        Spacer(Modifier.height(20.dp))

        imageUri?.let {


            Button(onClick = {
               val file = it.toFile(context)

                viewmodel.uploadImage(file)
            }) { Text("Upload Image") }
        }

    }


}





