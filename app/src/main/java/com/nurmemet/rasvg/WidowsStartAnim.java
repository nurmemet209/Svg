package com.nurmemet.rasvg;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.AnimationSet;

/**
 * Created by nurmemet on 9/29/2016.
 */

public class WidowsStartAnim extends Drawable {
    private Paint mPaint;
    private float t;
    private RectF mRect;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private int mStrokeWidth = 15;
    private Paint mBackgroundPaint;
    private float delta = 0.05F;


    public WidowsStartAnim() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(mStrokeWidth);
        mRect = new RectF(-150, -150, 150, 150);
        mPath = new Path();
        mPath.addArc(mRect, -90F, 359.9F);
        mPathMeasure = new PathMeasure(mPath, false);
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(Color.GREEN);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), mBackgroundPaint);
        canvas.save();
        canvas.translate(getIntrinsicWidth() / 2, getIntrinsicHeight() / 2);

        canvas.drawPath(getPath(3), mPaint);


        canvas.restore();

    }

    private Path getPath(int num) {
        Path dst = new Path();
        float x = 0;
        float s = 0;
        float y = 0;
        s = mPathMeasure.getLength();
        switch (num) {
            default:
            case 3:
                x = t - 3 * delta;
                if (x<0){
                    x=1+x;
                }
                y = -x * x + 2 * x;
                mPathMeasure.getSegment(s * y, s * y + 1, dst, true);
            case 2:
                x = t - 2 * delta;
                if (x<0){
                    x=1+x;
                }
                y = -x * x + 2 * x;
                mPathMeasure.getSegment(s * y, s * y + 1, dst, true);
            case 1:
                x = t - delta;
                if (x<0){
                    x=1+x;
                }
                y = -x * x + 2 * x;
                mPathMeasure.getSegment(s * y, s * y + 1, dst, true);
            case 0:
                x = t;
                y = -x * x + 2 * x;
                mPathMeasure.getSegment(s * y, s * y + 1, dst, true);
                break;
        }

        return dst;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }


    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(3000);
        animator.setRepeatCount(-1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                t = (float) animation.getAnimatedValue();
                invalidateSelf();
            }
        });
        animator.start();

    }

    @Override
    public int getIntrinsicWidth() {
        return 300 + 2 * mStrokeWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return 300 + 2 * mStrokeWidth;
    }
}
