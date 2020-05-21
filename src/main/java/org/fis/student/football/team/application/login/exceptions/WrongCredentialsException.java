package org.fis.student.football.team.application.login.exceptions;

public class WrongCredentialsException extends Exception{

    private String username;

    public WrongCredentialsException(){
        super("You entered a wrong password or selected incorrect role");
    }
}
