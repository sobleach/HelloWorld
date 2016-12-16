package com.example.thum.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    TextView tvHello;
    EditText etHello;
    EditText etNumber;
    EditText editText1;
    EditText editText2;
    TextView tvResult;
    Button btnCopy;
    Button btnCalculation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_linear); // Inflate from XML

        initInstances();
    }

    private void initInstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);

//        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
//        tvHello.setText(Html.fromHtml("<b>He</b> <a href=\"http://google.com\">https://google.com</a>"));
//        tvHello.setText("Yeah!");

        etHello = (EditText) findViewById(R.id.etHello);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etHello.setOnEditorActionListener(this);


        //Button Even > START
        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);


        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnCalculation = (Button) findViewById(R.id.btnCalculation);

        btnCalculation.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnCopy) {
            tvHello.setText(etNumber.getText());
        } else if (v == btnCalculation) {

            int val1 = 0;
            int val2 = 0;

            try {
                val1 = Integer.parseInt(editText1.getText().toString());
            } catch (ArithmeticException e) {

            }
            try {
                val2 = Integer.parseInt(editText2.getText().toString());
            } catch (ArithmeticException e) {

            }

            int sum = val1 + val2;
            tvResult.setText(String.valueOf(sum));

            Log.d("Calculation","Result = " + sum);

            Toast.makeText(MainActivity.this, "Result = "+sum ,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE:
            case EditorInfo.IME_ACTION_GO:
            case EditorInfo.IME_ACTION_NEXT:
                tvHello.setText(etHello.getText());
                break;
            default:
                break;
        }
            /*    if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Copy text in EditText to TextView
                    tvHello.setText(etHello.getText());
                    return true;
                }*/
        return false;
    }
}
