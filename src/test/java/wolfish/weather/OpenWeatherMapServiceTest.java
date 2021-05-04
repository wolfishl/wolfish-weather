package wolfish.weather;

import io.reactivex.rxjava3.core.Single;
import org.junit.Test;
import wolfish.weather.OpenWeatherMapFeed;
import wolfish.weather.OpenWeatherMapForecast;
import wolfish.weather.OpenWeatherMapService;
import wolfish.weather.OpenWeatherMapServiceFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OpenWeatherMapServiceTest {

    OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();

    @Test
    public void getCurrentWeather() {
        //given

        OpenWeatherMapService service = factory.newInstance();
        String location = "London";

        //when
        Single<OpenWeatherMapFeed> single = service.getCurrentWeather(location, "imperial");

        OpenWeatherMapFeed feed = single.blockingGet();

        //then
        assertNotNull(feed);
        assertNotNull(feed.main.temp);
        assertNotEquals(0, feed.main.temp);
        assertTrue(feed.main.temp < 150);
        assertEquals(location, feed.name);
        assertTrue(feed.dt > 0);
    }


    @Test
    public void getWeatherForecast() {
        //given

        OpenWeatherMapService service = factory.newInstance();
        String location = "London";

        //when
        Single<OpenWeatherMapForecast> single = service.getWeatherForecast(location, "imperial");

        OpenWeatherMapForecast forecast = single.blockingGet();

        //then
        assertNotNull(forecast);
        assertNotNull(forecast.list);
        assertFalse(forecast.list.isEmpty());
        assertTrue(forecast.list.get(0).dt > 0);
    }

}