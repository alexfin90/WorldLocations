package com.softdream.worldlocations.presentation.detail

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.worldlocations.R
import com.softdream.worldlocations.domain.GetLocationByIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor( @ApplicationContext  application: Context, private val getLocationByIDUseCase: GetLocationByIDUseCase, stateHandle: SavedStateHandle) : ViewModel() {

    //ViewModel only modify the UI state  and call domain layer

    private var _state = mutableStateOf((LocationDetailScreenState()))

    //expose the state to compose without possibility to modify state
    val state: State<LocationDetailScreenState> get() = _state

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
        val lastIDLocation = stateHandle.get<String>("location_id") ?: ""
        _state.value = _state.value.copy(lastIDLocation = lastIDLocation)
        getLocation(lastIDLocation)
    }

    private fun getLocation(id: String) {
        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle) {
            val location = getLocationByIDUseCase(id)
            _state.value = _state.value.copy(location = location, isLoading = false)
        }
    }


    fun retryGetLocation() {
        _state.value = _state.value.copy(isLoading = true, error = "")
        getLocation(_state.value.lastIDLocation)
    }
}