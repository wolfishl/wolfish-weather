package wolfish.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import wolfish.weather.OpenWeatherMapFeed;
import wolfish.weather.OpenWeatherMapForecast;
import wolfish.weather.OpenWeatherMapService;
import wolfish.weather.OpenWeatherMapServiceFactory;

public class WeatherController {
    @FXML
    TextField locationTextField;
    @FXML
    Label today;
    @FXML
    Label tomorrow;
    @FXML
    Label day2;
    @FXML
    Label day3;
    @FXML
    Label day4;
    @FXML
    RadioButton farenheit;
    @FXML
    RadioButton celsius;
    @FXML
    Label todayTemp;
    @FXML
    Label tomorrowTemp;
    @FXML
    Label day2Temp;
    @FXML
    Label day3Temp;
    @FXML
    Label day4Temp;

    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        farenheit.setToggleGroup(group);
        celsius.setToggleGroup(group);
    }


    public void search(ActionEvent e)
    {
        String location = locationTextField.getText();
        String units = celsius.isSelected() ? "metric" : "imperial";

        OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
        OpenWeatherMapService service = factory.newInstance();

        Disposable disposableCurrent = service.getCurrentWeather(location, units).subscribeOn((Schedulers.io()))
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onGetCurrentWeather,
                        this::onError);

        Disposable disposableForecast = service.getWeatherForecast(location, units)
                .subscribeOn((Schedulers.io()))
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onGetForecast,
                        this::onError);


        today.setText("Today");
        tomorrow.setText("Tomorrow");
        day2.setText("2 Days");
        day3.setText("3 Days");
        day4.setText("4 Days");


    }

    public void onGetCurrentWeather(OpenWeatherMapFeed feed){
        

    }

    public OpenWeatherMapForecast.HourlyForecast[] onGetForecast(OpenWeatherMapForecast forecast)
    {
        OpenWeatherMapForecast.HourlyForecast[] forecasts = new OpenWeatherMapForecast.HourlyForecast[4];
        forecasts[0] = forecast.getForcastFor(1);
        forecasts[1] = forecast.getForcastFor(2);
        forecasts[2] = forecast.getForcastFor(3);
        forecasts[3] = forecast.getForcastFor(4);
        return forecasts;
    }

    public void onError(Throwable throwable)
    {
        System.out.println("Error occured");
    }

}
