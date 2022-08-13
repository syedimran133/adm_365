package com.graph.adm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.graph.adm.R;

public class Login  extends AppCompatActivity {
LinearLayout btn_login;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_login);
            btn_login=(LinearLayout) findViewById(R.id.btn_login);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            });

}
}
