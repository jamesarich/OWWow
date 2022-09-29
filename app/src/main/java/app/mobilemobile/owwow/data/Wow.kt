package app.mobilemobile.owwow.data

import com.google.gson.annotations.SerializedName

data class Wow(
    @SerializedName("movie")
    val movie: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("movie_duration")
    val movieDuration: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("full_line")
    val fullLine: String,
    @SerializedName("current_wow_in_movie")
    val currentWowInMovie: Int,
    @SerializedName("total_wows_in_movie")
    val totalWowsInMovie: Int,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("video")
    val video: Video,
    @SerializedName("audio")
    val audio: String
)
