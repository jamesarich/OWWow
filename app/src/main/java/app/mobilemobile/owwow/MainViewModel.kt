package app.mobilemobile.owwow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mobilemobile.owwow.api.OWWowApi
import app.mobilemobile.owwow.api.OWWowService
import app.mobilemobile.owwow.data.Wow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val api: OWWowService = OWWowApi.retrofitService

    var wows: List<Wow> by mutableStateOf(listOf())
    lateinit var clickedWow: Wow


    init {
        fetchWows()
    }

    private fun fetchWows() {
        viewModelScope.launch {
            wows = api.getRandomWows(NUM_WOWS)
        }
    }

    companion object {
        private const val NUM_WOWS = 5
    }

    fun onWowClicked(wow: Wow){
        clickedWow = wow
    }
}
