package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String[] buttons = new String[]{"C","<-","%","/","7","8","9","*","4","5","6"
                            ,"-","1","2","3","+","~","0",".","="};
    TextView textViewInput, textViewExpression;

    private String[] operators = {"%", "/", "*", "-", "+","."};
    private List<String> arrayList = Arrays.asList(operators);
    private boolean isLastCharSpecial = false;
    private boolean evaluated = false;
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        bindComponents();;
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, buttons);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //input = textViewInput.getText().toString();

                if(!(buttons[i].equals("C") || buttons[i].equals("<-") || buttons[i].equals("=")))
                    input += buttons[i];

                if(evaluated){
                    textViewInput.setText("");
                    textViewExpression.setText("");
                    evaluated = false;
                }

                if(!isLastCharSpecial) {
                    textViewInput.setText(input);
                }
                else{
                    if(!arrayList.contains(buttons[i]))
                        textViewInput.setText(input);
                }
                if(arrayList.contains(buttons[i])){
                    isLastCharSpecial = true;
                }
                else {
                    isLastCharSpecial = false;
                }


                switch (buttons[i]){
                    case "C":
                        textViewInput.setText("");
                        textViewExpression.setText("");
                        input = "";
                        break;
                    case "<-":
                        if(!input.equals("")) {
                            input = input.substring(0, input.length() - 1);
                            textViewInput.setText(input);
                        }
                        break;
                    case "=":
                        int output = EvaluateExpression.evaluate(input);
                        textViewInput.setText("="+Integer.toString(output));
                        textViewExpression.setText(input);
                        input = ""+output;
                        evaluated = true;
                }
            }
        });

    }

    private void bindComponents(){
        textViewInput = findViewById(R.id.text_view_input);
        textViewExpression = findViewById(R.id.text_view_expre);
    }
}