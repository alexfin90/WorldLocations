package com.softdream.exposicily.presentation.list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.exposicily.ExpoSicilyApplication
import com.softdream.exposicily.R
import com.softdream.exposicily.data.LocationRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private val repository = LocationRepository()

    val state = mutableStateOf(LocationScreenState(listOf()))

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
        getLocations()
    }

    private fun getLocations() {
        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle) {
            val locations = repository.getAllLocations()
            state.value = state.value.copy(locations = locations, isLoading = false)
        }
    }

    fun retryGetLocations() {
        state.value = state.value.copy(isLoading = true, error = "")
        getLocations()
    }


}