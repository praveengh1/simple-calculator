package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    // Variables to hold the operands and type of calculations
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";
    private Double resultnum = null;

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";

    CalculationUtils utils = new CalculationUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);

        Button buttonDot = (Button) findViewById(R.id.buttonDecimal);
        Button buttonEquals = (Button) findViewById(R.id.buttonEqual);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonSquare = (Button) findViewById(R.id.buttonSquare);
        Button buttonSquareRoot = (Button) findViewById(R.id.buttonSquareRoot);
        Button buttonSin = (Button) findViewById(R.id.buttonSin);
        Button buttonCos = (Button) findViewById(R.id.buttonCos);
        Button buttonTan = (Button) findViewById(R.id.buttonTan);
        Button buttonPercent = (Button) findViewById(R.id.buttonPercent);
        Button buttonMod = (Button) findViewById(R.id.buttonMod);
        Button buttonFact = (Button) findViewById(R.id.buttonFactorial);
        Button buttonLog = (Button) findViewById(R.id.buttonLog);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
        buttonSquare.setOnClickListener(opListener);
        buttonSquareRoot.setOnClickListener(opListener);
        buttonSin.setOnClickListener(opListener);
        buttonCos.setOnClickListener(opListener);
        buttonTan.setOnClickListener(opListener);
        buttonPercent.setOnClickListener(opListener);
        buttonMod.setOnClickListener(opListener);
        buttonFact.setOnClickListener(opListener);
        buttonLog.setOnClickListener(opListener);

        Button buttonNeg = (Button) findViewById(R.id.buttonNeg);

        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                if (value.length() == 0) {
                    newNumber.setText("-");
                } else {
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        newNumber.setText(doubleValue.toString());
                    } catch (NumberFormatException e) {
                        // newNumber was a minus sign or a dot, so clear it.
                        newNumber.setText("");
                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        displayOperation.setText(pendingOperation);
    }

    private void performOperation(Double value, String operation) {

        if (null == operand1) {
            operand1 = value;
            /* Vasudevan - Phase 1: Adding SquareRoot function */
            if (operation.equals("√")) {
                pendingOperation = operation;
                operand1 = utils.findSquareRoot(operand1);
            }
            else if (operation.equals("Sin")) {
                pendingOperation = operation;
                operand1 = utils.findSin(operand1);
            }
            else if (operation.equals("Cos")) {
                pendingOperation = operation;
                operand1 = utils.findCos(operand1);
            }
            else if (operation.equals("Tan")) {
                pendingOperation = operation;
                operand1 = utils.findTan(operand1);
            }
            /* Vasudevan - Phase 2: Handling factorial calculation */
            if (operation.equals("!")) {
                pendingOperation = operation;
                operand1 = utils.findFact(operand1);
            }
            /* Monisha - phase 2 : Handling Log functionality for base 10 */
            if (operation.equals("Log")){
                pendingOperation=operation;
                operand1=utils.findLog(operand1);

            }
        } else {
            operand2 = value;
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }


            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= operand2;
                    }
                    break;
                case "x":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;
                /* Nicki - Phase 1: Adding Percent and Mod */
                case "%":
                    System.out.println("Inside switch: ");
              operand1 = utils.findPercentage(operand1, operand2);
                    break;
                case "Mod":
                    System.out.println("Inside Mod switch: ");
                    operand1 = utils.findModulus(operand1, operand2);
                    break;

                /* Vasudevan - Phase 1: Adding Square function */
                case "^":
                    operand1 = utils.findSquare(operand1, operand2);

            }
        }

        result.setText(operand1.toString());
        newNumber.setText("");
    }

}