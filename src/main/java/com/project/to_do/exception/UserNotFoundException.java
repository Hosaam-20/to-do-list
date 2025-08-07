package com.project.to_do.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){}
    public UserNotFoundException(String msg){super(msg);}

}
