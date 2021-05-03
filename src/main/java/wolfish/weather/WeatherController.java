package wolfish.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import wolfish.weather.OpenWeatherMapFeed;
import wolfish.weather.OpenWeatherMapForecast;
import wolfish.weather.OpenWeatherMapService;
import wolfish.weather.OpenWeatherMapServiceFactory;

import java.util.List;

public class WeatherController {
    @FXML
    TextField locationTextField;
    @FXML
    private List<Label> days;
    @FXML
    RadioButton farenheit;
    @FXML
    RadioButton celsius;
    @FXML
    private List<Label> temperature;

    @FXML
    private List<ImageView> images;


    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        farenheit.setToggleGroup(group);
        celsius.setToggleGroup(group);
        farenheit.setSelected(true);
    }


    public void search(ActionEvent e)
    {

        String location = locationTextField.getText();
        String units = celsius.isSelected() ? "metric" : "imperial";

        OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
        OpenWeatherMapService service = factory.newInstance();

        Disposable disposableForecast = service.getWeatherForecast(location, units)
                .subscribeOn((Schedulers.io()))
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onGetForecast,
                        this::onError);
 }


    public void onGetForecast(OpenWeatherMapForecast forecast)
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {

                for (int index = 0; index < days.size(); index++)
                {
                    days.get(index).setText(forecast.getForcastFor(index).getDate() + "");
                    temperature.get(index).setText(forecast.getForcastFor(index).main.temp + "");
                    images.get(index).setImage(new Image(forecast.getForcastFor(index).weather.get(0).getIconUrl()));
                }
            }
        });
    }

    public void onError(Throwable throwable)
    {
        System.out.println("Error occured");
    }

}
