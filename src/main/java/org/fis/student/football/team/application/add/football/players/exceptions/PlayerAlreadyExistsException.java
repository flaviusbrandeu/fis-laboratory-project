package org.fis.student.football.team.application.add.football.players.exceptions;

public class PlayerAlreadyExistsException extends Exception{
    private String name;

    public PlayerAlreadyExistsException(String name) {
        super(String.format("Player %s already exists!", name));
        this.name = name;
    }

    public String getUsername() {
        return name;
    }
}
