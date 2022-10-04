package app.mobilemobile.owwow.data

import com.google.gson.annotations.SerializedName

data class Wow(
    @SerializedName("movie")
    var movie: String,
    @SerializedName("year")
    var year: Int,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("director")
    var director: String,
    @SerializedName("character")
    var character: String,
    @SerializedName("movie_duration")
    var movieDuration: String,
    @SerializedName("timestamp")
    var timestamp: String,
    @SerializedName("full_line")
    var fullLine: String,
    @SerializedName("current_wow_in_movie")
    var currentWowInMovie: Int,
    @SerializedName("total_wows_in_movie")
    var totalWowsInMovie: Int,
    @SerializedName("poster")
    var poster: String,
    @SerializedName("video")
    var video: Video,
    @SerializedName("audio")
    var audio: String,
)
