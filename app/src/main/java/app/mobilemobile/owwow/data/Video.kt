package app.mobilemobile.owwow.data

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("1080p")
    val quality1080p: String,
    @SerializedName("720p")
    val quality720p: String,
    @SerializedName("480p")
    val quality480p: String,
    @SerializedName("360p")
    val quality360p: String
)
