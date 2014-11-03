package com.pickupsports.myapplication;

public interface TaskCallback<T> {

    public void success(T result);

    public void error(Exception e);

}