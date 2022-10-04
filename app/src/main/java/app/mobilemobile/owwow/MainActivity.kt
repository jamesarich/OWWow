package app.mobilemobile.owwow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.mobilemobile.owwow.ui.theme.OWWowTheme
import app.mobilemobile.owwow.ui.views.WowDetail
import app.mobilemobile.owwow.ui.views.WowList

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainContent(
                navController = navController,
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

@Suppress("FunctionNaming")
@Composable
fun MainContent(
    navController: NavHostController,
    mainViewModel: MainViewModel = viewModel(),
    siteButtonClicked: () -> Unit
) {
    OWWowTheme {
        NavHost(navController = navController, startDestination = "wowList") {
            composable("wowList") {
                WowList(
                    viewModel = mainViewModel,
                    onWowClicked = { wow ->
                        mainViewModel.onWowClicked(wow)
                        navController.navigate("wowDetail")
                    },
                    onSiteButtonClicked = { siteButtonClicked() },
                )
            }
            composable("wowDetail") {
                WowDetail(viewModel = mainViewModel)
            }
        }
    }
}

