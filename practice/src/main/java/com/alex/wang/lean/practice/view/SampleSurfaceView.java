package com.alex.wang.lean.practice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * TODO: document your custom view class.
 */
public class SampleSurfaceView extends SurfaceView {
    private SurfaceHolder.Callback mHolderCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mDrawTask.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mDrawTask.exit();
        }
    };

    private DrawTask mDrawTask;

    public SampleSurfaceView(Context context) {
        this(context, null);
    }

    public SampleSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SampleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDrawTask = new DrawTask(getHolder());
        getHolder().addCallback(mHolderCallback);
    }

    private class DrawTask extends Thread {
        private Paint mPaint;
        private SurfaceHolder mSurfaceHolder;
        private boolean mIsRunning;

        public DrawTask(SurfaceHolder surfaceHolder) {
            mSurfaceHolder = surfaceHolder;
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.RED);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(10f);
            mIsRunning = true;
        }

        @Override
        public void run() {
            int width;
            int height;
            float radius = 0f;

            mIsRunning = true;

            while (mIsRunning) {
                Canvas canvas = mSurfaceHolder.lockCanvas();
                width = canvas.getWidth();
                height = canvas.getHeight();

                canvas.drawColor(Color.WHITE);
                canvas.drawCircle(width / 2, height / 2, radius++, mPaint);
                mSurfaceHolder.unlockCanvasAndPost(canvas);

                if (radius >= (width /2)) {
                    radius = 0;
                }
            }
        }

        public void exit() {
            mIsRunning = false;
        }
    }
}