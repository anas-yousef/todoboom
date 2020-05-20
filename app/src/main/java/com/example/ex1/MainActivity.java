package com.example.ex1;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Todo> todoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        final Button button = findViewById(R.id.button);
        final TextView textEntered = findViewById(R.id.enteredText);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final MyTasks myTasks = (MyTasks) getApplicationContext();
        //myTasks.sharedTodoList.clearTodoList();

        //myTasks.sharedTodoList.saveTodoList(todoList);
        todoList = myTasks.sharedTodoList.retrieveTodoList();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        if (savedInstanceState != null) {
//            boolean[] clicked = savedInstanceState.getBooleanArray("clicked");
//            ArrayList<String> tasks = savedInstanceState.getStringArrayList("todoList");
//            assert tasks != null;
//
//            for (int i = 0; i < tasks.size(); i++) {
//                assert clicked != null;
//                todoList.add(new Todo(tasks.get(i), clicked[i]));
//
//            }
            todoList = savedInstanceState.getParcelableArrayList("todolist");
        }
        final TodoAdapter todoAdapter = new TodoAdapter(context, todoList);
        recyclerView.setAdapter(todoAdapter);
        //myTasks.sharedTodoList.saveTodoList(todoList);
        Log.i("Size of TODO List", String.valueOf(todoList.size()));
        todoList = myTasks.sharedTodoList.retrieveTodoList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = textEntered.getText().toString();
                if(message.isEmpty())
                {
                    Toast.makeText(context, "You must write something!", Toast.LENGTH_SHORT).show();
                }
                else{

                    textEntered.setText("");
                    //todoAdapter.addTodoItem(new Todo(message, false));
                    todoList.add(new Todo(message, false));
                    todoAdapter.notifyDataSetChanged();
                    ArrayList<Todo> temp = todoList;
                    myTasks.sharedTodoList.saveTodoList(todoList);
                    todoAdapter.notifyDataSetChanged();
                    //todoAdapter.notifyItemInserted(todoAdapter.getItemCount() - 1);
                    //todoAdapter.setTodo(todoList);
                    recyclerView.setAdapter(todoAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        ArrayList<String> tasks = new ArrayList<>();
//        boolean[] clicked = new boolean[todoList.size()];
//        int i = 0;
//        for (Todo todo : todoList) {
//            tasks.add(todo.getTodoText());
//            clicked[i] = todoList.get(i).isClicked();
//            i++;
//        }
//        outState.putStringArrayList("todoList", tasks);
//        outState.putBooleanArray("clicked", clicked);
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("todolist", new ArrayList<Todo>(TodoAdapter.getList()));
    }
}