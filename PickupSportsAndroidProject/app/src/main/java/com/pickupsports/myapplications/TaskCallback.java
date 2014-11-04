package com.pickupsports.myapplications;

public interface TaskCallback<T> {

    public void success(T result);

    public void error(Exception e);

}