package app.mobilemobile.owwow

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainContent(navController = navController, MainViewModel(), {})
        }
    }

    // Unit test
    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithText("A random set of Owen Wilson's \"wow\" exclamations in movies.")
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_verifyWowDetailDestination() {
        composeTestRule
            .onAllNodesWithContentDescription(
                "Movie poster",
                substring = true,
                useUnmergedTree = true
            )[0].performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "wowDetail")
    }
}
