package app.mobilemobile.owwow

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import app.mobilemobile.owwow.data.Wow
import app.mobilemobile.owwow.ui.theme.OWWowTheme
import app.mobilemobile.owwow.ui.views.WowDetail
import app.mobilemobile.owwow.ui.views.WowList
import app.mobilemobile.owwow.ui.views.testWow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OWWowComposeTest {
    private val wows = mutableListOf<Wow>()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        for (i in 0..4) {
            val tempWow = testWow.copy()
            tempWow.movie = "Test Movie $i"
            wows.add(tempWow)
        }
    }

    @Test
    fun wowList_verifyViewsDisplayed() {
        composeTestRule.setContent {
            OWWowTheme {
                WowList(wows = wows, {}, {})
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
    fun wowDetail_verifyViewsDisplayed() {
        composeTestRule.setContent {
            OWWowTheme {
                WowDetail(wow = wows[0])
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
