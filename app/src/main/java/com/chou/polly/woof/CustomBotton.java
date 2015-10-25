package com.chou.polly.woof;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;

public class CustomBotton extends Button{
    public enum ButtonColor {
        pink, gray, blue
    }

    public CustomBotton(Context context) {
        super(context);
        init();
    }

    public CustomBotton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        ButtonColor value = ButtonColor.values()[arr.getInt(R.styleable.CustomButton_btnColor, ButtonColor.pink.ordinal())];
        updateBg(value);
    }

    public void setBackgroundColor(ButtonColor color) {
        updateBg(color);
    }

    private void init() {
        // setTextColor(Color.WHITE);
        // setTextSize(18);
        // ...
    }

    private void updateBg(ButtonColor color) {
        if(color == null) color = ButtonColor.pink;

        switch (color) {
            case gray:
                setBackgroundResource(R.drawable.btn_material_gray);
                break;
            case blue:
                setBackgroundResource(R.drawable.btn_material_blue);
                break;
            case pink:
            default:
                setBackgroundResource(R.drawable.btn_material_pink);
                break;
        }
    }
}
