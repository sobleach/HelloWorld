package com.example.thum.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    Button btnOK;

    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initInstances();

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);
        tvResult.setText("Result = " + sum);

        Bundle bundle = intent.getBundleExtra("cBundle");
        int x = bundle.getInt("x");
        int y = bundle.getInt("y");
        int z = bundle.getInt("z");

        CoordinateSerializable c2 = (CoordinateSerializable) intent.getSerializableExtra("cSerializable");

        CoordinateParcelable c3 = intent.getParcelableExtra("cParcelable");
    }

    private void initInstances() {
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        System.out.println(v.toString());
        if (v == btnOK) {
            finish();
        }

    }
}
