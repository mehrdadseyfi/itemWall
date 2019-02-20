package meh.example.root.itemwall;

import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import meh.example.root.itemwall.Auth.LogInActivity;

public class SplashActivity extends AppCompatActivity {
    boolean havetoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        havetoken = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("havetoken", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (havetoken) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LogInActivity.class));
                }
            }

        }, 2500);
    }
}
