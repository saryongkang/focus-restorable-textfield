package com.example.focusrestorabletextfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.focusrestorabletextfield.ui.theme.FocusRestorableTextfieldTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.systemBars))

        Column(
            Modifier.consumeWindowInsets(
                WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
            )
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldPanel()
            Spacer(modifier = Modifier.height(32.dp))
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.ime))
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }
}

@Composable
fun TextFieldPanel(modifier: Modifier = Modifier) {
    val (text1, setText1) = rememberSaveable { mutableStateOf("") }
    val (text2, setText2) = rememberSaveable { mutableStateOf("") }
    val (text3, setText3) = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Default TextFields")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text1,
            onValueChange = setText1,
            modifier = Modifier.widthIn(max = 240.dp).fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text2,
            onValueChange = setText2,
            modifier = Modifier.widthIn(max = 240.dp).fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text3,
            onValueChange = setText3,
            modifier = Modifier.widthIn(max = 240.dp).fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FocusRestorableTextfieldTheme {
        HomeScreen()
    }
}
