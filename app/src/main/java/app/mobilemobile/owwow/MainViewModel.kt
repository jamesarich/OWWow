package app.mobilemobile.owwow

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mobilemobile.owwow.api.OWWowApi
import app.mobilemobile.owwow.api.OWWowService
import app.mobilemobile.owwow.data.Wow
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api: OWWowService = OWWowApi.retrofitService

    var error: String? by mutableStateOf(null)
    var wows: List<Wow> by mutableStateOf(listOf())
    lateinit var clickedWow: Wow


    init {
        fetchWows()
    }

    private fun fetchWows() {
        viewModelScope.launch {
            val response = api.getRandomWows(NUM_WOWS)
            response.onSuccess {
                error = null
                wows = data
            }.onFailure {
                error = "Oh Wow, something went wrong!"
            }
        }
    }

    companion object {
        private const val NUM_WOWS = 20
    }

    fun onWowClicked(wow: Wow){
        clickedWow = wow
    }
}
