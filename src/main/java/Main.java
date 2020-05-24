import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.student.football.team.application.login.services.UserService;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserService.loadUsersFromFile();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("loginScreen.fxml")));
        primaryStage.setTitle("Football Team Application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
