package com.vcyberpunk.module2mirea

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class WaterTrackerUiTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun openWaterTracker() {
        composeRule.onNodeWithText("Вода").performClick()
    }

    @Test
    fun addWaterIncreasesAmountBy250Ml() {
        composeRule.onNodeWithText("+250 мл").assertIsDisplayed().performClick().performClick()
        composeRule.onNodeWithText("500 мл").assertIsDisplayed()
    }

    @Test
    fun completingSuccessfulDayResetsWaterAndIncreasesStreak() {
        repeat(6) { composeRule.onNodeWithText("+250 мл").performClick() }
        composeRule.onNodeWithText("Завершить день").performClick()

        composeRule.onNodeWithText("0 мл").assertIsDisplayed()
        composeRule.onNodeWithText("Дней подряд с нормой: 1").assertIsDisplayed()
    }

    @Test
    fun completingDayBelowGoalResetsStreak() {
        repeat(6) { composeRule.onNodeWithText("+250 мл").performClick() }
        composeRule.onNodeWithText("Завершить день").performClick()
        repeat(4) { composeRule.onNodeWithText("+250 мл").performClick() }
        composeRule.onNodeWithText("Завершить день").performClick()

        composeRule.onNodeWithText("0 мл").assertIsDisplayed()
        composeRule.onNodeWithText("Дней подряд с нормой: 0").assertIsDisplayed()
    }
}
