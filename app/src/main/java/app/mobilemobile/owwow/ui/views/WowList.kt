package app.mobilemobile.owwow.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mobilemobile.owwow.MainViewModel
import app.mobilemobile.owwow.R
import app.mobilemobile.owwow.data.Video
import app.mobilemobile.owwow.data.Wow
import app.mobilemobile.owwow.ui.theme.OWWowTheme
import app.mobilemobile.owwow.ui.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Suppress("FunctionNaming", "CoroutineCreationDuringComposition")
@Composable
fun WowList(
    viewModel: MainViewModel = viewModel(),
    onWowClicked: (wow: Wow) -> Unit,
    onSiteButtonClicked: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,
        ) {
            LazyColumn(
                state = listState
            ) {
                item {
                    Header()
                }
                itemsIndexed(viewModel.wows) { _, wow ->
                    WowCard(wow, onWowClicked)
                }
                item {
                    Footer(onSiteButtonClicked)
                }
            }
        }
        if (!viewModel.error.isNullOrEmpty()) {
            scope.launch {
                snackbarHostState.showSnackbar(viewModel.error ?: "An Error Occurred")
            }
        }
    }
}

@Suppress("FunctionNaming")
@Composable
fun Header() {
    Text(
        modifier = Modifier.padding(8.dp),
        text = stringResource(R.string.header_text),
        style = Typography.titleLarge,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Suppress("FunctionNaming")
@Composable
fun Footer(
    onSiteButtonClicked: () -> Unit
) {
    Spacer(modifier = Modifier.height(64.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.footer_text),
            style = Typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onSiteButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(R.string.visit_site))
        }
        Text(
            text = stringResource(R.string.disclaimer),
            style = Typography.bodySmall,
            textAlign = TextAlign.Justify
        )
    }
}

@Suppress("FunctionNaming")
@Composable
fun WowCard(
    wow: Wow,
    onWowClicked: (wow: Wow) -> Unit,
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onWowClicked(wow)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                imageModel = wow.poster,
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit,
                    contentDescription = "Movie poster for ${wow.movie}"
                ),
                modifier = Modifier.fillMaxWidth(),
            )
            Text(text = wow.movie, style = Typography.titleMedium)
            Text(text = "Wow ${wow.currentWowInMovie} of ${wow.totalWowsInMovie}")
        }
    }
}

@Suppress("FunctionNaming")
@Preview(showBackground = true)
@Composable
fun WowCardPreview() {
    OWWowTheme {
        WowCard(testWow, {})
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
