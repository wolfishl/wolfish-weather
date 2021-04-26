import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather?APPID=425d7121d8c44e11076ae0ff62a25f9f")
    Single<OpenWeatherMapFeed> getCurrentWeather(@Query("q") String location);
}
