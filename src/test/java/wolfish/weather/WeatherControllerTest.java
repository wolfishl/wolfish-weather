package wolfish.weather;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeatherControllerTest {

    private List<Label> days;
    private List<Label> temperature;
    private List<ImageView> images;
    private Date date = new Date();

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }


    public WeatherController givenWeatherController()
    {
        WeatherController controller = new WeatherController();
        controller.farenheit = mock(RadioButton.class);
        controller.celsius = mock(RadioButton.class);
        controller.locationTextField = mock(TextField.class);
        days = Arrays.asList(
                mock(Label.class),
                mock(Label.class),
                mock(Label.class)
        );

        controller.days  = days;
        temperature = Arrays.asList(
                mock(Label.class),
                mock(Label.class),
                mock(Label.class)
        );
        controller.temperature = temperature;
        images = Arrays.asList(
                mock(ImageView.class),
                mock(ImageView.class),
                mock(ImageView.class)
        );
        controller.images = images;

        return controller;
    }

    @Test
    public void initialize()
    {
        //given
        WeatherController controller = givenWeatherController();

        //when
        controller.initialize();

        //then
        verify(controller.farenheit).setSelected(true);
    }

    //This test is incomplete...
    @Test
    public void settingGUI()
    {
        //given
        WeatherController controller = givenWeatherController();
        OpenWeatherMapForecast forecast = mock(OpenWeatherMapForecast.class);
        OpenWeatherMapForecast.HourlyForecast hourly = mock(OpenWeatherMapForecast.HourlyForecast.class);
        doReturn(hourly).when(forecast).getForcastFor(anyInt());
        List<OpenWeatherMapForecast.HourlyForecast.Weather> weather =
                Arrays.asList(
                        mock(OpenWeatherMapForecast.HourlyForecast.Weather.class),
                        mock(OpenWeatherMapForecast.HourlyForecast.Weather.class),
                        mock(OpenWeatherMapForecast.HourlyForecast.Weather.class)
                );
        //doReturn(weather).when(hourly).get(anyIn);
        doReturn(date).when(hourly).getDate();
        String url = "http://openweathermap.org/img/wn/100@2x.png";
        doReturn(url).when(weather.get(anyInt())).getIconUrl();

        //when
        controller.settingGUI(forecast);

        //then
        for(Label label : controller.days)
        {
            verify(label, times(1)).setText(date + "");
        }
        for (Label label : controller.temperature)
        {
            ;
        }
        for (ImageView image : images)
        {
            verify(image, times(1)).setImage(new Image("http://openweathermap.org/img/wn/100@2x.png"));
        }
    }

}