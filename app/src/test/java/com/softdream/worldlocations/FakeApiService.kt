import com.softdream.worldlocations.data.MockContent
import com.softdream.worldlocations.data.remote.LocationApiService
import com.softdream.worldlocations.data.remote.RemoteLocation
import kotlinx.coroutines.delay

class FakeApiService : LocationApiService {

    override suspend fun getLocations(): List<RemoteLocation> {
        //simulate api call
        delay(500)
        return MockContent.getRemoteLocations()
    }

    override suspend fun getLocation(id: Int): Map<String, RemoteLocation> {
        TODO("Not yet implemented")
    }
}