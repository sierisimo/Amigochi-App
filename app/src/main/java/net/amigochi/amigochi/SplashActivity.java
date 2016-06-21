package net.amigochi.amigochi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.amigochi.amigochi.features.home.MainActivity;
import net.amigochi.amigochi.features.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ((TextView) findViewById(R.id.tv_ac_splash_version)).setText(BuildConfig.VERSION_NAME);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getSharedPreferences(BuildConfig.SHARED_PREFERENCES_NAME, MODE_PRIVATE).getBoolean("isLogged", false)) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 3000);
    }
}
