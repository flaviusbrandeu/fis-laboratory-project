package org.fis.student.football.team.application.login.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.fis.student.football.team.application.login.exceptions.UserDoesNotExistException;
import org.fis.student.football.team.application.login.exceptions.WrongCredentialsException;
import org.fis.student.football.team.application.login.services.UserService;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox<String> role;
    @FXML
    private Button loginButton;
    @FXML
    private StackPane rootPane;
    @FXML
    private GridPane loginGridPane;
    @FXML
    private GridPane teamManagerMenu;
    @FXML
    private GridPane footballAgentMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll("Team Manager","Football Agent");
    }


    @FXML
    public void handleLoginAction(ActionEvent event) throws Exception{
        teamManagerMenu= FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("teamManagerMenu.fxml")));
        footballAgentMenu=FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("footballAgentMenu.fxml")));
        try{
            UserService.verifyCredentials(usernameField.getText(),passwordField.getText(),role.getValue());

            switch (role.getValue()){
                case "Team Manager":
                    rootPane.getChildren().add(teamManagerMenu);
                    break;
                case "Football Agent":
                    rootPane.getChildren().add(footballAgentMenu);
                    break;
            }
            rootPane.getChildren().remove(loginGridPane);
        } catch(UserDoesNotExistException e){
            loginMessage.setText(e.getMessage());
        } catch (WrongCredentialsException e){
            loginMessage.setText(e.getMessage());
        }

//        switch (role.getValue())
//        {
//            case "Team Manager":
//
//        }
    }
}
