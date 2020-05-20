package com.example.ex1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SharedTodoList {

    private ArrayList<Todo> todoList;
    private SharedPreferences sp;


    SharedTodoList(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        todoList = new ArrayList<>();
    }

    void clearTodoList()
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
    void saveTodoList(ArrayList<Todo> tasks) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SharedTodoList", gson.toJson(tasks));
        editor.apply();
    }

    ArrayList<Todo> retrieveTodoList() {
        Gson gson = new Gson();
        String spSavedTodoList = sp.getString("SharedTodoList", null);
        if (spSavedTodoList != null) {
            todoList = gson.fromJson(spSavedTodoList, new TypeToken<ArrayList<Todo>>() {
            }.getType());
        }
        return todoList;
    }


}
