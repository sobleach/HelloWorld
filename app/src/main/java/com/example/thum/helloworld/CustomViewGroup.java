package com.example.thum.helloworld;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by Thum on 12/18/2016 AD.
 */

public class CustomViewGroup extends FrameLayout {

    private Button btnHello;

    public CustomViewGroup(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    @TargetApi(21)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        // Inflate Layout Here
        inflate(getContext(),R.layout.sample_layout,this);
    }

    private void initInstances(){
        // findViewById here
        btnHello = (Button) findViewById(R.id.btnCustomViewGroupHello);
    }

    public void setButtonText(String text){
        btnHello.setText(text);
    }
}
