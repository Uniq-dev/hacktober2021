package com.example.imagelover.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.imagelover.R;

public class MainActivity extends AppCompatActivity {
    private Button Shapes,Images;
    private Animation top,bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Shapes=findViewById(R.id.Shapes);
        Images=findViewById(R.id.images);
        top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
       Images.setAnimation(bottom);
        Shapes.setAnimation(top);
        Shapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Shapes.class);
                v.getContext().startActivity(intent);

            }
        });
        Images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Photos.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}