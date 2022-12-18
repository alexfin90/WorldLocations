package com.softdream.worldlocations

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LocationViewModelTest {
/*
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
                locations = MockContent.getDomainLocation(),
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
    }*/
}