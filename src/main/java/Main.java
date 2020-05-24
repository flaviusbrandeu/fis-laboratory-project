import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.student.football.team.application.add.football.players.services.FootballPlayerService;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FootballPlayerService.loadPlayersFromFile();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("addFootballPlayers.fxml")));
        primaryStage.setTitle("Add football players");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
