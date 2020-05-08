package org.fis.student.football.team.application.register.users;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.student.football.team.application.register.users.services.UserService;

import java.util.Objects;

public class RegisterUsersMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserService.loadUsersFromFile();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("registerUsers.fxml")));
        primaryStage.setTitle("Register users");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}

