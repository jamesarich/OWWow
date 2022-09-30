package app.mobilemobile.owwow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.mobilemobile.owwow.data.Video
import app.mobilemobile.owwow.data.Wow
import app.mobilemobile.owwow.ui.theme.OWWowTheme
import app.mobilemobile.owwow.ui.views.WowDetail
import app.mobilemobile.owwow.ui.views.WowList

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            OWWowTheme {
                NavHost(navController = navController, startDestination = "wowList") {
                    composable("wowList") {
                        WowList(mainViewModel = mainViewModel, navController = navController)
                    }
                    composable("wowDetail") {
                        WowDetail(wow = mainViewModel.clickedWow)
                    }
                }
            }
        }
    }
}

