package com.example.myapplication

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityRouterTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupNavigation() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainActivityRouter(navController = navController)
        }
    }

    @Test
    fun navigateToLengthConversionViaButtonClick() {
        composeTestRule.onNode(
            hasText(ConversionCategory.LENGTH.name),
            useUnmergedTree = true,
        ).assertIsDisplayed().performClick()

        assertEquals(
            "/convert/{category}",
            navController.currentDestination?.route,
        )
        assertEquals(
            ConversionCategory.LENGTH,
            navController.backStack.last().arguments
                ?.apiAwareGetSerializable<ConversionCategory>("category"),
        )
    }
}
