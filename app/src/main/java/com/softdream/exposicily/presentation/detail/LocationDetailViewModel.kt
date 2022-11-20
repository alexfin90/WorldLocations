package com.softdream.exposicily.presentation.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.exposicily.ExpoSicilyApplication
import com.softdream.exposicily.R
import com.softdream.exposicily.data.LocationRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LocationDetailViewModel(stateHandle: SavedStateHandle) : ViewModel() {
    private val repository = LocationRepository()
    var state = mutableStateOf((LocationDetailScreenState()))

    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.d(
                    this.javaClass.simpleName,
                    exception.message ?: ExpoSicilyApplication.getAppContext()
                        .getString(R.string.generic_error)
                )
                state.value = state.value.copy(
                    isLoading = false,
                    error = exception.message ?: ExpoSicilyApplication.getAppContext()
                        .getString(R.string.generic_error)
                )
                exception.printStackTrace()
            }
        }


    init {
        val lastIDLocation = stateHandle.get<Int>("location_id") ?: 0
        state.value = state.value.copy(lastIDLocation = lastIDLocation)
        getLocation(lastIDLocation)
    }

    private fun getLocation(id: Int) {
        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle) {
            val location = repository.getLocationByID(id)
            state.value = state.value.copy(location = location, isLoading = false)
        }
    }


    fun retryGetLocation() {
        state.value = state.value.copy(isLoading = true, error = "")
        getLocation(state.value.lastIDLocation)
    }
}