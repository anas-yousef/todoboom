package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.enteredText);
        Button button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String enteredText = editText.getText().toString();
               if (enteredText.equals(""))
               {
                   textView.setText("Enter Something Other Than Empty String");
               }
               else {
                   textView.setText(enteredText);
               }
               editText.setText("");

           }
       });
    }
}
