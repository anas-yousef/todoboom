package com.example.ex1;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

public class Todo implements Parcelable {
    private String todoText;
    private boolean clicked;


    public TextView textView;

    protected Todo(Parcel in) {
        todoText = in.readString();
        clicked = in.readByte() != 0;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public String getTodoText() {
        return todoText;
    }

    public boolean isClicked() {
        return clicked;
    }

    public Todo(String todoText,boolean clicked) {
        this.todoText = todoText;
        textView=null;
        this.clicked = clicked;
    }
    public void setClicked(){
        clicked = true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(todoText);
        dest.writeByte((byte) (clicked ? 1 : 0));
    }
}