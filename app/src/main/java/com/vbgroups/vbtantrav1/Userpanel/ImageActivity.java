package com.vbgroups.vbtantrav1.Userpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vbgroups.vbtantrav1.R;

public class ImageActivity extends AppCompatActivity {

    String imgid;
    ImageView img;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        img = findViewById(R.id.img);
        Intent intent = getIntent();
        imgid = intent.getStringExtra("imgid");

       if (imgid!=null) {
           if (!imgid.isEmpty()) {
               Glide.with(getApplicationContext()).load(imgid).into(img);
           }
           scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
       }
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            img.setScaleX(mScaleFactor);
            img.setScaleY(mScaleFactor);
            return true;
        }
    }
}
