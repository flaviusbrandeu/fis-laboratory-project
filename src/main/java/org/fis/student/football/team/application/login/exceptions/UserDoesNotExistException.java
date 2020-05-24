package org.fis.student.football.team.application.login.exceptions;

public class UserDoesNotExistException extends Exception{

    private String username;

    public UserDoesNotExistException(String username){
        super(String.format("Username %s does not correspond to any account!",username));
    }
}
