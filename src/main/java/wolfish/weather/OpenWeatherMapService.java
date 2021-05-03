package wolfish.weather;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wolfish.weather.OpenWeatherMapFeed;
import wolfish.weather.OpenWeatherMapForecast;

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather?APPID=425d7121d8c44e11076ae0ff62a25f9f")
    Single<OpenWeatherMapFeed> getCurrentWeather(@Query("q") String location,
                                                 @Query("units") String units);

    @GET("/data/2.5/forecast?APPID=425d7121d8c44e11076ae0ff62a25f9f")
    Single<OpenWeatherMapForecast> getWeatherForecast(@Query("q") String location,
                                                      @Query("units") String units);
}
