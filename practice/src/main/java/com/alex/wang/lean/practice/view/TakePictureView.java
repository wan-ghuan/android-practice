package com.alex.wang.lean.practice.view;

import android.content.Context;
import android.graphics.Camera;
import android.util.AttributeSet;
import android.view.TextureView;

public class TakePictureView extends TextureView {
    private Camera mCamera;
    
    public TakePictureView(Context context) {
        this(context, null);
    }

    public TakePictureView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TakePictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
