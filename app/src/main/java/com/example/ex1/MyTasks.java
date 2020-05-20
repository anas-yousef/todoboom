package com.example.ex1;

import android.app.Application;

public class MyTasks extends Application {
    static SharedTodoList sharedTodoList;

    public void onCreate() {
        super.onCreate();
        sharedTodoList = new SharedTodoList(this);
    }
}
