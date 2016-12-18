package com.example.thum.helloworld;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.TypeVariable;

/**
 * Created by Thum on 12/18/2016 AD.
 */

public class CustomView extends View {

    boolean isBlue;
    boolean isDown;
    GestureDetector gestureDetector;

    public CustomView(Context context) {
        super(context);
        init();

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initWithAttr(attrs, 0, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initWithAttr(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
        initWithAttr(attrs, defStyleAttr, defStyleRes);
    }

    public void initWithAttr(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        //ต้องมีการ clear ค่าเสมอ
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomView,
                defStyleAttr,
                defStyleRes);

        try {
            isBlue = typedArray.getBoolean(R.styleable.CustomView_isBlue, false);
        } finally {
            typedArray.recycle();
        }
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // Do whatever when onDown is True
                getParent().requestDisallowInterceptTouchEvent(true);

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //ตอนที่จิ้ม แล้วปล่อย (Action UP)
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {
                isBlue = !isBlue;
                invalidate();
                return true;
            }
        });

        // Enable Click Mode
        setClickable(true);

        //After this call, if it's not clickable, it will be clickable
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        if (isBlue) {
            p.setColor(0xFF0000FF); // Blue
        } else {
            p.setColor(0xFFFF0000); // Red
        }
//        p.setColor(0xFFFF0000); // #AARRGGBB
        canvas.drawLine(0, 0,
                getMeasuredWidth(), getMeasuredHeight(),
                p);

        if (isDown) {
            p.setColor(0xFF00FF00);

            //Conver dp to px
            float px = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    5,
                    getContext().getResources().getDisplayMetrics());
            p.setStrokeWidth(px);

            //Draw Line
            canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), 0, p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass event to GestureDetector
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                invalidate(); // clear view และทำการวาดขึ้นมาใหม่
                return true;
            case MotionEvent.ACTION_MOVE:
                isDown = true;
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                isDown = false;
                invalidate();
                return true;
            case MotionEvent.ACTION_CANCEL:
                isDown = false;
                invalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }
}
