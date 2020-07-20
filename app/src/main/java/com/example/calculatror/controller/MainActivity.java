package com.example.calculatror.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculatror.R;
import com.example.calculatror.exceptions.InvalidInputException;
import com.example.calculatror.model.Calculator;

import static com.example.calculatror.model.Calculator.calculate;

public class MainActivity extends AppCompatActivity {

    Button[] mButtonNumber = new Button[10];
    Button mButtonDelete;
    Button mButtonDivide;
    Button mButtonMultiply;
    Button mButtonSubtract;
    Button mButtonAdd;
    Button mButtonResult;
    Button mButtonPoint;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    private void findViews() {
        mButtonNumber[0] = findViewById(R.id.button0);
        mButtonNumber[1] = findViewById(R.id.button1);
        mButtonNumber[2] = findViewById(R.id.button2);
        mButtonNumber[3] = findViewById(R.id.button3);
        mButtonNumber[4] = findViewById(R.id.button4);
        mButtonNumber[5] = findViewById(R.id.button5);
        mButtonNumber[6] = findViewById(R.id.button6);
        mButtonNumber[7] = findViewById(R.id.button7);
        mButtonNumber[8] = findViewById(R.id.button8);
        mButtonNumber[9] = findViewById(R.id.button9);
        mButtonDelete = findViewById(R.id.button_delete);
        mButtonDivide = findViewById(R.id.button_divide);
        mButtonMultiply = findViewById(R.id.button_multiply);
        mButtonSubtract = findViewById(R.id.button_subtract);
        mButtonAdd = findViewById(R.id.button_add);
        mButtonResult = findViewById(R.id.button_equal);
        mButtonPoint = findViewById(R.id.button_point);
        mTextView = findViewById(R.id.text_view);
    }

    private void setListeners() {
        for (int i = 0; i < mButtonNumber.length; i++) {
            final int finalI = i;
            mButtonNumber[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    mTextView.setText(mTextView.getText().toString() + finalI);
                }
            });
        }

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText("");
            }
        });

        mButtonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printSymbol('÷');
            }
        });

        mButtonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printSymbol('×');
            }
        });

        mButtonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printSymbol('-');
            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printSymbol('+');
            }
        });

        mButtonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printSymbol('.');
            }
        });

        mButtonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = mTextView.getText().toString();
                try {
                    double result = Calculator.calculate(currentText);
                    mTextView.setText(String.valueOf(result));
                } catch (InvalidInputException e) {
                    Toast.makeText(MainActivity.this,
                            "Invalid input!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void printSymbol(char sym) {
        String currentText = mTextView.getText().toString();
        if (currentText == null || currentText == "") {
            if (sym == '-')
                mTextView.setText(mTextView.getText().toString() + sym);
            else
                return;
        } else {
            char lastChar = currentText.charAt(currentText.length() - 1);
            if (lastChar == '+'
                    || lastChar == '-'
                    || lastChar == '×'
                    || lastChar == '÷'
                    || lastChar == '.'
            )
                return;
            else {
                mTextView.setText(mTextView.getText().toString() + sym);
            }
        }
    }

}









