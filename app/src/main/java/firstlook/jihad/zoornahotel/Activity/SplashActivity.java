package firstlook.jihad.zoornahotel.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.appcompat.app.AppCompatActivity;

import firstlook.jihad.zoornahotel.R;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoImageView = findViewById(R.id.imageViewLogo);
        logoImageView.setBackgroundResource(R.drawable.splash_logo_animation);

        AnimationDrawable logoAnimation = (AnimationDrawable) logoImageView.getBackground();
        logoAnimation.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}
