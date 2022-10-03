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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import app.mobilemobile.owwow.data.Video
import app.mobilemobile.owwow.data.Wow
import app.mobilemobile.owwow.ui.theme.OWWowTheme
import app.mobilemobile.owwow.ui.theme.Typography
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@Suppress("FunctionNaming")
@Composable
fun WowList(wows: List<Wow>, onWowClicked: (wow: Wow) -> Unit, onSiteButtonClicked: () -> Unit, listState: LazyListState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            state = listState
        ) {
            item {
                Header()
            }
            itemsIndexed(wows) { _, wow ->
                WowCard(wow, onWowClicked)
            }
            item {
                Footer(onSiteButtonClicked)
            }
        }
    }
}

@Suppress("FunctionNaming")
@Composable
fun Header() {
    Text(
        modifier = Modifier.padding(8.dp),
        text = "A random set of Owen Wilson's \"wow\" exclamations in movies.",
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
            "This app is utilizing The Owen Wilson Wow API.",
            style = Typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onSiteButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Visit Site")
        }
        Text(
            "Disclaimer: This app is not affiliated, associated, authorized, endorsed by, or in any way officially connected with Owen Wilson, or any of his subsidiaries or affiliates. All motion pictures, products, and brands mentioned on this website are the respective trademarks and copyrights of their owners.",
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
                imageOptions = ImageOptions(contentScale = ContentScale.Fit),
                modifier = Modifier.fillMaxWidth()
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
