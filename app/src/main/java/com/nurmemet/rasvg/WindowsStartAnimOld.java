package com.nurmemet.rasvg;

import android.animation.ValueAnimator;
import android.app.admin.SystemUpdatePolicy;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by nurmemet on 10/1/2016.
 */

public class WindowsStartAnimOld extends Drawable {
    private Paint mPain;
    private int mLineHeight = 15;
    private Path mPath;
    private final int mWidth = 600;
    private final int mHeight = 600;
    private PathMeasure mPathMeasure;
    private float t = 0;
    private Path mDesPath;
    private Paint mBackgroundPaint;

    public WindowsStartAnimOld() {
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(Color.GREEN);
        mPath = new Path();
        mDesPath = new Path();
        mPain = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPain.setColor(Color.RED);
        mPain.setStrokeCap(Paint.Cap.ROUND);
        mPain.setStyle(Paint.Style.STROKE);
        mPain.setStrokeWidth(mLineHeight);
        mPath.moveTo(0, mHeight / 2);
        mPath.lineTo(mWidth, mHeight / 2);
        mPathMeasure = new PathMeasure(mPath, false);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), mBackgroundPaint);
        mDesPath.reset();
        mPathMeasure.getSegment(mPathMeasure.getLength() * t, mPathMeasure.getLength() * t + 1, mDesPath, true);
        canvas.drawPath(mDesPath, mPain);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(2000);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                t = value;
                invalidateSelf();
            }
        });
        animator.start();
    }

    @Override
    public void setAlpha(int alpha) {
        mPain.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPain.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mHeight;
    }
}
