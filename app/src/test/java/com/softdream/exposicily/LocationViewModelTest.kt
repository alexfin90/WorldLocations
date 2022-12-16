package com.softdream.exposicily

import FakeApiService
import com.softdream.exposicily.data.LocationRepository
import com.softdream.exposicily.domain.GetLocationsUseCase
import com.softdream.exposicily.presentation.list.LocationScreenState
import com.softdream.exposicily.presentation.list.LocationViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class LocationViewModelTest {

    // Using the test dispatcher and not the main like in prod env
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun initialStateTest() = scope.runTest {
        val viewModel = getViewModel()
        val initialState = viewModel.state.value
        assert(
            initialState == LocationScreenState(
                locations = emptyList(),
                isLoading = true,
                error = ""
            )
        )
    }

    @Test
    fun stateWithContextTest() = scope.runTest {
        val testVM = getViewModel()
        advanceUntilIdle()
        val currentSate = testVM.state.value
        assert(
            currentSate == LocationScreenState(
                locations = DummyContent.getDomainLocation(),
                isLoading = false,
                error = ""
            )
        )
    }

    private fun getViewModel(): LocationViewModel {
        val locationRepository = LocationRepository(
            restInterface = FakeApiService(),
            locationDao = FakeRoomDao(),
            dispatcher
        )
        val useCaseLocation = GetLocationsUseCase(locationRepository)
        return LocationViewModel(
            getLocationsUseCase = useCaseLocation,
            dispatcher = dispatcher,
            null
        )
    }
}