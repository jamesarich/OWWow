package app.mobilemobile.owwow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.mobilemobile.owwow.ui.theme.OWWowTheme
import app.mobilemobile.owwow.ui.views.WowDetail
import app.mobilemobile.owwow.ui.views.WowList

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainContent(
                navController = navController,
                mainViewModel = mainViewModel,
                siteButtonClicked = { siteButtonClicked() })
        }
    }

    private fun siteButtonClicked() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://owen-wilson-wow-api.herokuapp.com")
            )
        )
    }

}

@Composable
fun MainContent(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    siteButtonClicked: () -> Unit
) {
    OWWowTheme {
        NavHost(navController = navController, startDestination = "wowList") {
            composable("wowList") {
                WowList(
                    wows = mainViewModel.wows,
                    onWowClicked = { wow ->
                        mainViewModel.onWowClicked(wow)
                        navController.navigate("wowDetail")
                    },
                    onSiteButtonClicked = { siteButtonClicked() },
                )
            }
            composable("wowDetail") {
                WowDetail(wow = mainViewModel.clickedWow)
            }
        }
    }
}

