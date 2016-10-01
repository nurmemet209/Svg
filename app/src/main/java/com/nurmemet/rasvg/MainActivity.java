package com.nurmemet.rasvg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView refreshAnim;
    ImageView pathAnim;
    ImageView mWindowsAnim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshAnim = (ImageView) findViewById(R.id.vector_anim_view);
        AnimatedVectorDrawable anim = (AnimatedVectorDrawable) refreshAnim.getDrawable();
        anim.start();


        pathAnim = (ImageView) findViewById(R.id.test);
        WidowsStartAnim an = new WidowsStartAnim();
        pathAnim.setBackground(an);
        an.startAnim();

        mWindowsAnim2= (ImageView) findViewById(R.id.test2);
        WindowsStartAnimOld old=new WindowsStartAnimOld();
        mWindowsAnim2.setBackground(old);
        old.startAnim();

    }
}
