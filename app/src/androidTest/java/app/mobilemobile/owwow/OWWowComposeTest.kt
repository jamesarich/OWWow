package app.mobilemobile.owwow

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mobilemobile.owwow.ui.theme.OWWowTheme
import app.mobilemobile.owwow.ui.views.WowDetail
import app.mobilemobile.owwow.ui.views.WowList
import app.mobilemobile.owwow.ui.views.testWow
import org.junit.Rule
import org.junit.Test

class OWWowComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun wowList_verifyViewsDisplayed() {
        composeTestRule.setContent {
            val viewModel = viewModel<MainViewModel>()
            OWWowTheme {
                WowList(
                    viewModel = viewModel,
                    onWowClicked = {},
                    onSiteButtonClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            "A random set",
            ignoreCase = true,
            substring = true,
            useUnmergedTree = true
        )
            .assertIsDisplayed()
    }

    @Test
    fun wowList_verifyErrorDisplayed() {
        composeTestRule.setContent {
            val viewModel = viewModel<MainViewModel>()
            viewModel.error = "Oh Wow, something went wrong!"
            OWWowTheme {
                WowList(
                    viewModel = viewModel,
                    onWowClicked = {},
                    onSiteButtonClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            "Oh wow",
            ignoreCase = true,
            substring = true,
            useUnmergedTree = true
        )
            .assertIsDisplayed()
    }

    @Test
    fun wowDetail_verifyViewsDisplayed() {
        composeTestRule.setContent {
            val viewModel = viewModel<MainViewModel>()
            viewModel.clickedWow = testWow
            OWWowTheme {
                WowDetail(viewModel)
            }
        }

        composeTestRule.onNodeWithText(
            "Wow!",
            ignoreCase = true,
            substring = true,
            useUnmergedTree = true
        )
            .assertIsDisplayed()
    }
}
