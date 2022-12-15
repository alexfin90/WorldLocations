package com.softdream.exposicily.presentation.list

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.exposicily.R
import com.softdream.exposicily.domain.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(@ApplicationContext application: Context, private val getLocationsUseCase : GetLocationsUseCase) : ViewModel() {
    //ViewModel only modify the UI state  and call domain layer

    private val _state = mutableStateOf(LocationScreenState(listOf()))
    //expose the state to compose without possibility to modify state
    val state : State<LocationScreenState> get() = _state

    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.d(
                    this.javaClass.simpleName,
                    exception.message ?: application.getString(R.string.generic_error)
                )
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: application.getString(R.string.generic_error)
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
            val locations = getLocationsUseCase()
            _state.value = _state.value.copy(locations = locations, isLoading = false)
        }
    }

    fun retryGetLocations() {
        _state.value = _state.value.copy(isLoading = true, error = "")
        getLocations()
    }


}