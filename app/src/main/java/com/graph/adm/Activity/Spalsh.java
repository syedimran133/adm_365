package com.graph.adm.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.graph.adm.R;

public class Spalsh extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        new Handler().postDelayed(() -> {
            if (isNetworkAvailable(Spalsh.this)) {
                Intent intent = new Intent(Spalsh.this, Login.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
