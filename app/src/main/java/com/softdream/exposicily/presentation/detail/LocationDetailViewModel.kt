package com.softdream.exposicily.presentation.detail

import android.util.Log
import androidx.compose.runtime.State
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
    //ViewModel only modify the UI state  and call domain layer
    private val repository = LocationRepository()
    private var _state = mutableStateOf((LocationDetailScreenState()))
    //expose the state to compose without possibility to modify state
    val state: State<LocationDetailScreenState>  get() = _state

    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.d(
                    this.javaClass.simpleName,
                    exception.message ?: ExpoSicilyApplication.getAppContext()
                        .getString(R.string.generic_error)
                )
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: ExpoSicilyApplication.getAppContext()
                        .getString(R.string.generic_error)
                )
                exception.printStackTrace()
            }
        }


    init {
        val lastIDLocation = stateHandle.get<Int>("location_id") ?: 0
        _state.value = _state.value.copy(lastIDLocation = lastIDLocation)
        getLocation(lastIDLocation)
    }

    private fun getLocation(id: Int) {
        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle) {
            val location = repository.getLocationByID(id)
            _state.value = _state.value.copy(location = location, isLoading = false)
        }
    }


    fun retryGetLocation() {
        _state.value = _state.value.copy(isLoading = true, error = "")
        getLocation(_state.value.lastIDLocation)
    }
}