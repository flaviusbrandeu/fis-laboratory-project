package org.fis.student.football.team.application.login.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.fis.student.football.team.application.login.exceptions.UserDoesNotExistException;
import org.fis.student.football.team.application.login.exceptions.WrongCredentialsException;
import org.fis.student.football.team.application.login.services.UserService;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll("Team Manager","Football Agent");
    }


    @FXML
    public void handleLoginAction(ActionEvent event){
        try{
            UserService.verifyCredentials(usernameField.getText(),passwordField.getText(),role.getValue());
            loginMessage.setText("You're logged in!");
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
