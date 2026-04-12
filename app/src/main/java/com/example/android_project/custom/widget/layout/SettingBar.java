package com.example.android_project.custom.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_project.R;

public class SettingBar extends FrameLayout {
    private TextView mStartView;
    private TextView mEndView;
    private View mLineView;
    private LinearLayout mMainLayout;

    public SettingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SettingBar);
        LayoutInflater.from(context).inflate(R.layout.setting_bar, this, true);

        mMainLayout = new LinearLayout(getContext());
        mStartView = new TextView(getContext());
        mEndView = new TextView(getContext());
        mLineView = new View(getContext());

        mMainLayout.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL));

        LinearLayout.LayoutParams startLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
        startLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        startLayoutParams.weight = 1;
        mStartView.setLayoutParams(startLayoutParams);

        LinearLayout.LayoutParams endLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        endLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        mEndView.setLayoutParams(endLayoutParams);

        mStartView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        mEndView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);

        // Set start text
        String text = ta.getString(R.styleable.SettingBar_bar_startText);
        if (mStartView != null && text != null) {
            mStartView.setText(text);
        }

        mLineView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM));
        mMainLayout.addView(mStartView);
        mMainLayout.addView(mEndView);

        addView(mMainLayout, 0);
        addView(mLineView, 1);
        mMainLayout.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                mEndView.setMaxWidth((right - left) / 3 * 2);
            }
        });
        ta.recycle();
    }
}
