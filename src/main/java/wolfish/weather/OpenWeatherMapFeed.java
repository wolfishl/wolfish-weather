package wolfish.weather;

import java.util.Date;
import java.util.List;

public class OpenWeatherMapFeed {

    WeatherMain main;
    String name;
    long dt;

    static class WeatherMain{
        double temp;
    }

    /*
    Converts Unix time (in seconds) to UTC
     */
    public Date getTime()
    {
        return new Date(dt*1000);

    }

}
