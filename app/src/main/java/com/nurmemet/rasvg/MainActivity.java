package com.nurmemet.rasvg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView refreshAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshAnim= (ImageView) findViewById(R.id.vector_anim_view);
        AnimatedVectorDrawable anim= (AnimatedVectorDrawable) refreshAnim.getDrawable();
        anim.start();
    }
}
