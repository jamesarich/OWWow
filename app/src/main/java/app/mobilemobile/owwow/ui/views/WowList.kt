package app.mobilemobile.owwow.ui.views

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
import androidx.navigation.compose.rememberNavController
import app.mobilemobile.owwow.MainViewModel
import app.mobilemobile.owwow.data.Video
import app.mobilemobile.owwow.data.Wow
import app.mobilemobile.owwow.ui.theme.OWWowTheme


@Suppress("FunctionNaming")
@Composable
fun WowList(mainViewModel: MainViewModel, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            itemsIndexed(mainViewModel.wows) { _, wow ->
                WowCard(wow, mainViewModel::onWowClicked, navController)
            }
        }
    }
}

@Suppress("FunctionNaming")
@Composable
fun WowCard(
    wow: Wow,
    onWowClicked: (wow: Wow) -> Unit,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable {
                onWowClicked(wow)
                navController.navigate("wowDetail")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(text = "${wow.movie} - Wow ${wow.currentWowInMovie} of ${wow.totalWowsInMovie}")
        }
    }
}

@Suppress("FunctionNaming")
@Preview(showBackground = true)
@Composable
fun WowCardPreview() {
    OWWowTheme {
        val navController = rememberNavController()
        WowCard(testWow, {}, navController)
    }
}

val testVideo = Video(
    quality1080p = "1080",
    quality720p = "760,",
    quality480p = "480",
    quality360p = "360"
)
val testWow = Wow(
    movie = "Wow Movie",
    year = 2023,
    releaseDate = "march 1",
    director = "Wow Director",
    character = "Wow Character",
    movieDuration = "120mins",
    timestamp = "timestamp",
    fullLine = "wow",
    currentWowInMovie = 1,
    totalWowsInMovie = 3,
    poster = "poster",
    video = testVideo,
    audio = "audio"
)
