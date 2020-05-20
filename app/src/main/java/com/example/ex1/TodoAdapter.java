package com.example.ex1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private Context context;
    private static ArrayList<Todo> todoItems;
    private AlertDialog.Builder alertDialog;



    public TodoAdapter(Context context, ArrayList<Todo> todoItemsNew) {
        todoItems = todoItemsNew;
        this.context = context;


    }

    static ArrayList<Todo> getList() {
        return todoItems;
    }

    public void addTodoItem(Todo todo)
    {
        todoItems.add(todo);
        notifyDataSetChanged();
    }

    void removeTodoItem(Todo todo) {
        todoItems.remove(todo);
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Todo todoItem = todoItems.get(position);
        final int taskNum = position + 1;
        if (todoItem.textView == null) {
            todoItem.textView = new TextView(context);
            todoItem.textView.setText(holder.textView.getText());
            todoItem.textView.setAlpha(holder.textView.getAlpha());
        }
        holder.textView.setText(taskNum + "- " + todoItem.getTodoText());
        holder.textView.setAlpha(todoItem.textView.getAlpha());
        if (todoItem.isClicked()) {
            holder.textView.setAlpha(0.2f);
        }
        else {
            holder.textView.setAlpha(0.9f);
        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!todoItem.isClicked()) {
                    todoItem.setClicked();
                    Toast.makeText(context, "TODO " + todoItem.getTodoText() + " is now DONE. BOOM!",
                            Toast.LENGTH_SHORT).show();
                    holder.textView.setAlpha(0.4f);
                    todoItem.textView.setAlpha(holder.textView.getAlpha());
                }
            }
        });

        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Remove TODO Task");
                alertDialog.setMessage("Are you sure you want to delete?");
                alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        todoItems.remove(todoItems.get(holder.getAdapterPosition()));
                        notifyDataSetChanged();
                        //MyTasks.sharedTodoList.saveTodoList(todoItems);
                        //todoItems = MyTasks.sharedTodoList.retrieveTodoList();
                    }
                });

                alertDialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}