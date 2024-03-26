package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxOpciones;
    private RadioGroup radioGroupOpciones;
    private Operacion operando;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxOpciones = findViewById(R.id.checkBoxOpciones);
        radioGroupOpciones = findViewById(R.id.radioGroupOpciones);
        display = findViewById(R.id.display);

        checkBoxOpciones.setOnClickListener(view -> {
            if (checkBoxOpciones.isChecked()){
                radioGroupOpciones.setVisibility(View.GONE);
            } else {
                radioGroupOpciones.setVisibility(View.VISIBLE);
            }
        });

    }

/*    @Override
    public void onClick(View view) {
        if(display.getText().toString().equals("0"))
            display.setText(((Button)view).getText());
        else

            display.setText(String.valueOf(display.getText())+((Button)view).getText());
    }*/

}