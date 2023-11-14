package com.example.focusrestorabletextfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.focusrestorabletextfield.ui.theme.FocusRestorableTextfieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            FocusRestorableTextfieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.safeDrawingPadding(),
                    //color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
