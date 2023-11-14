/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
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
            FocusRestorableTextFieldPanel()
            Spacer(modifier = Modifier.height(32.dp))
            TextFieldPanel()
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.ime))
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }
}

@Composable
fun TextFieldPanel(modifier: Modifier = Modifier) {
    val (text1, setText1) = rememberSaveable { mutableStateOf("") }
    val (text2, setText2) = rememberSaveable { mutableStateOf("") }

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
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FocusRestorableTextFieldPanel(modifier: Modifier = Modifier) {
    val (text1, setText1) = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val (text2, setText2) = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val (text3, setText3) = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val onSearchTriggered = {
        keyboardController?.hide()
    }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Focus Restorable TextFields")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text1,
            onValueChange = setText1,
            modifier = Modifier
                .widthIn(max = 240.dp)
                .fillMaxWidth()
                .focusRestorer(focusRequester1),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text2,
            onValueChange = setText2,
            modifier = Modifier
                .widthIn(max = 240.dp)
                .fillMaxWidth()
                .focusRestorer(focusRequester2, false),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text3,
            onValueChange = setText3,
            modifier = Modifier
                .widthIn(max = 240.dp)
                .fillMaxWidth()
                .focusRestorer(focusRequester3, false)
                .onKeyEvent {
                    if (it.key == Key.Enter) {
                        onSearchTriggered()
                        true
                    } else {
                        false
                    }
                },
            placeholder = {
                Text("Search field...")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchTriggered()
                },
            ),
            maxLines = 1,
            singleLine = true,
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
