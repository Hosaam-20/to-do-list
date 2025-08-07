package com.project.to_do.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(){}
    public CategoryNotFoundException(String msg){super(msg);}
}
