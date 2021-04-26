import io.reactivex.rxjava3.core.Single;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenWeatherMapServiceTest {

    @Test
    public void getCurrentWeather() {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        //when
        Single<OpenWeatherMapFeed> single = service.getCurrentWeather("London");

        OpenWeatherMapFeed feed = single.blockingGet();

        //then
        assertNotNull(feed);
        assertNotNull(feed.main.temp);
        assertNotEquals(0, feed.main.temp);
    }

}