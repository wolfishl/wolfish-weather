package wolfish.weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WeatherApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        OpenWeatherMapService service = new OpenWeatherMapServiceFactory().newInstance();
        WeatherController controller = new WeatherController(service);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/weather_application.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("5 Day Weather");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[]args){
        launch(args);
    }
}
