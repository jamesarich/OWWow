package app.mobilemobile.owwow

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    private fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainContent(navController = navController, viewModel(), {})
        }
    }

    // Unit test
    @Test
    fun appNavHost_verifyStartDestination() {
        setupAppNavHost()
        composeTestRule.onNodeWithText("A random set of Owen Wilson's \"wow\" exclamations in movies.")
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_verifyWowDetailDestination() {
        setupAppNavHost()
        composeTestRule.onAllNodesWithContentDescription(
                "Movie poster", substring = true, useUnmergedTree = true
            )[0].performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "wowDetail")
    }
}
