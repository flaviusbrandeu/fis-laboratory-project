package org.fis.student.football.team.application.add.football.players.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.fis.student.football.team.application.add.football.players.exceptions.PlayerAlreadyExistsException;
import org.fis.student.football.team.application.add.football.players.services.FootballPlayerService;

public class AddFootballPlayersController {
    @FXML
    private Text message;
    @FXML
    private TextField usualPostField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField playersAgentField;
    @FXML
    private TextField marketPriceField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField passField;
    @FXML
    private TextField shotField;
    @FXML
    private TextField defenceField;
    @FXML
    private TextField dribblingField;
    @FXML
    private TextField physicField;

    public void handleAddPlayerAction(){
        try {
            FootballPlayerService.addPlayer(nameField.getText(),
                    usualPostField.getText(),
                    playersAgentField.getText(),
                    marketPriceField.getText(),
                    salaryField.getText(),
                    shotField.getText(),
                    defenceField.getText(),
                    passField.getText(),
                    dribblingField.getText(),
                    physicField.getText());
            message.setText("Added new football player to the championship!");
        } catch (PlayerAlreadyExistsException e) {
            message.setText(e.getMessage());
        } catch (NumberFormatException e){
            message.setText(e.getMessage());
        }

    }
}
