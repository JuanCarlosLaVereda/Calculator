package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CheckBox checkBoxOpciones;
    private RadioGroup radioGroupOpciones;
    private Operacion operacion;
    private TextView display;
    private Button buttonClear;
    private Double operando;
    private Button buttonSuma;
    private Button buttonResta;
    private Button buttonMultiplicacion;
    private Button buttonDivision;
    private Button buttonIgual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxOpciones = findViewById(R.id.checkBoxOpciones);
        radioGroupOpciones = findViewById(R.id.radioGroupOpciones);
        display = findViewById(R.id.display);
        buttonClear = findViewById(R.id.buttonClear);
        buttonSuma = findViewById(R.id.buttonSuma);
        buttonResta = findViewById(R.id.buttonResta);
        buttonMultiplicacion = findViewById(R.id.buttonMultiplicacion);
        buttonDivision = findViewById(R.id.buttonDivision);
        buttonIgual = findViewById(R.id.buttonIgual);

        radioGroupOpciones.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.radioButtonSuma){
                buttonSuma.setVisibility(View.INVISIBLE);
                buttonResta.setVisibility(View.VISIBLE);
                buttonMultiplicacion.setVisibility(View.VISIBLE);
                buttonDivision.setVisibility(View.VISIBLE);
            } else if (i==R.id.radioButtonResta) {
                buttonResta.setVisibility(View.INVISIBLE);
                buttonMultiplicacion.setVisibility(View.VISIBLE);
                buttonDivision.setVisibility(View.VISIBLE);
                buttonSuma.setVisibility(View.VISIBLE);
            } else if (i == R.id.radioButtonMultiplicacion) {
                buttonMultiplicacion.setVisibility(View.INVISIBLE);
                buttonResta.setVisibility(View.VISIBLE);
                buttonDivision.setVisibility(View.VISIBLE);
                buttonSuma.setVisibility(View.VISIBLE);
            } else if (i == R.id.radioButtonDivision){
                buttonDivision.setVisibility(View.INVISIBLE);
                buttonResta.setVisibility(View.VISIBLE);
                buttonMultiplicacion.setVisibility(View.VISIBLE);
                buttonSuma.setVisibility(View.VISIBLE);
            } else {
                buttonResta.setVisibility(View.VISIBLE);
                buttonMultiplicacion.setVisibility(View.VISIBLE);
                buttonSuma.setVisibility(View.VISIBLE);
                buttonDivision.setVisibility(View.VISIBLE);
            }
        });
        

        checkBoxOpciones.setOnClickListener(view -> {
            if (checkBoxOpciones.isChecked()){
                radioGroupOpciones.clearCheck();
                radioGroupOpciones.setVisibility(View.GONE);
            } else {
                radioGroupOpciones.setVisibility(View.VISIBLE);
            }
        });

        buttonClear.setOnClickListener(view -> {
            display.setText("0");
            operando = 0.0;
            operacion = null;
        });

        buttonSuma.setOnClickListener(view -> {
            operando = Double.parseDouble(display.getText().toString());
            operacion = Operacion.SUMA;
            display.setText("0");
        });
        buttonResta.setOnClickListener(view -> {
            operando = Double.parseDouble(display.getText().toString());
            operacion = Operacion.RESTA;
            display.setText("0");
        });
        buttonMultiplicacion.setOnClickListener(view -> {
            operando = Double.parseDouble(display.getText().toString());
            operacion = Operacion.MULTIPLICACION;
            display.setText("0");
        });
        buttonDivision.setOnClickListener(view -> {
            operando = Double.parseDouble(display.getText().toString());
            operacion = Operacion.DIVISION;
            display.setText("0");
        });
        
        buttonIgual.setOnClickListener(view -> {
            if (operacion == Operacion.SUMA){
                display.setText(String.valueOf(operando + Double.parseDouble(display.getText().toString())));
            } else if (operacion == Operacion.RESTA) {
                display.setText(String.valueOf(operando - Double.parseDouble(display.getText().toString())));
            } else if (operacion == Operacion.MULTIPLICACION) {
                display.setText(String.valueOf(operando * Double.parseDouble(display.getText().toString())));
            } else if (operacion == Operacion.DIVISION) {
                if (Double.parseDouble(display.getText().toString()) == 0){
                    display.setText("0");
                    Toast toast = Toast.makeText(this, "INDETERMINADO", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    display.setText(String.valueOf(operando/Double.parseDouble(display.getText().toString())));
                }
            } else {
                display.setText("0");
                Toast toast = Toast.makeText(this, "Ha ocurrido un error vuelve a intentarlo.", Toast.LENGTH_SHORT);
                toast.show();
            }
            operacion = null;
            operando = 0.0;
        });


    }

    @Override
    public void onClick(View view) {
        if(display.getText().toString().equals("0"))
            display.setText(((Button)view).getText());
        else

            display.setText(String.valueOf(display.getText())+((Button)view).getText());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putDouble("operando", operando);
        outState.putSerializable("operacion", (Serializable)operacion);
        outState.putString("display", display.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        display.setText(savedInstanceState.getString("display"));
        operando = savedInstanceState.getDouble("operando");
        operacion = (Operacion)savedInstanceState.getSerializable("operacion");
    }

}