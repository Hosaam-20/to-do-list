package com.project.to_do.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(){}
    public TaskNotFoundException(String msg){super(msg);}
}
