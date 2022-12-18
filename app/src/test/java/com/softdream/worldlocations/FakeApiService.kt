import com.softdream.worldlocations.data.remote.LocationApiService
import com.softdream.worldlocations.data.remote.RemoteLocation

class FakeApiService : LocationApiService {

    override suspend fun getLocations(): List<RemoteLocation> {
        //simulate api call
        TODO("Not yet implemented")
      /*  delay(500)
        return MockContent.getRemoteLocations()*/
    }

    override suspend fun getLocationByCode(code: String): List<RemoteLocation> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocation(id: Int): Map<String, RemoteLocation> {
        TODO("Not yet implemented")
    }
}