package com.example.focusrestorabletextfield

import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.espresso.device.DeviceInteraction.Companion.setScreenOrientation
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import androidx.test.espresso.device.rules.ScreenOrientationRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val screenOrientationRule: ScreenOrientationRule = ScreenOrientationRule(ScreenOrientation.PORTRAIT)

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun FocusableTextField_focusByDefaultSetting() {
        composeRule.setContent {
            HomeScreen()
        }
        composeRule.onNodeWithTag("FocusRestorableTextField1").assertIsFocused()
    }

    @Test
    fun FocusableTextField_nofocusByDefaultSetting() {
        composeRule.setContent {
            HomeScreen()
        }
        composeRule.onNodeWithTag("FocusRestorableTextField2").assertIsNotFocused()
        composeRule.onNodeWithTag("FocusRestorableTextField3").assertIsNotFocused()
    }

    @Test
    fun FocusableTextField_maintainFocusAfterRotate() {
        composeRule.setContent {
            HomeScreen()
        }
        onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
        composeRule.onNodeWithTag("FocusRestorableTextField1").assertIsFocused()
    }
}
