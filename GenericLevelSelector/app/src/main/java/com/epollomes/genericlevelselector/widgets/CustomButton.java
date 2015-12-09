package com.epollomes.genericlevelselector.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.epollomes.genericlevelselector.R;

public class CustomButton extends FrameLayout {

    ImageView imageView;


    TextView textView;
    Rect rect = null;
    public IOnButtonClicked mListener2;
    int levelNumber = 0;

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        imageView = new ImageView(context);

        imageView.setImageResource(R.drawable.level_selector_pressed);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                .LayoutParams
                .WRAP_CONTENT);
        frameLayoutParams.gravity = Gravity.CENTER;
        this.addView(imageView);


        textView = new TextView(context);
        textView.setText("12");
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "fonts/kush.ttf");
        textView.setTypeface(type);
        FrameLayout.LayoutParams frameLayoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup
                        .LayoutParams
                        .WRAP_CONTENT);
        frameLayoutParams2.gravity = Gravity.CENTER;
        this.addView(textView);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventType = event.getAction();
                if ((eventType == MotionEvent.ACTION_DOWN)) {
                    if (CustomButton.this.getScaleX() == 1.0f) {
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        CustomButton.this.setScaleX(0.8f);
                        CustomButton.this.setScaleY(0.8f);
                    }
                    return true;
                } else if (eventType == MotionEvent.ACTION_UP) {
                    if (CustomButton.this.getScaleY() == 0.8f) {
                        CustomButton.this.setScaleX(1.0f);
                        CustomButton.this.setScaleY(1.0f);
                        Log.d("XXX", "onTouch: button Clicked");
                        if (mListener2 != null) {
                            mListener2.onLoadLevel();
                        }
                    }
                    return true;
                } else if (eventType == MotionEvent.ACTION_MOVE) {
                    if (CustomButton.this.getScaleY() == 0.8f) {
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            CustomButton.this.setScaleX(1.0f);
                            CustomButton.this.setScaleY(1.0f);
                        }
                    }
                    return true;
                } else if (eventType == MotionEvent.ACTION_CANCEL) {
                    if (CustomButton.this.getScaleY() == 0.8f) {
                        CustomButton.this.setScaleX(1.0f);
                        CustomButton.this.setScaleY(1.0f);
                    }
                    return true;
                }
                return true;

            }
        });

    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
