package com.example.bahung.vtask1.view.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.utils.ChangeColorSttBar;

public class LoginActivity extends AppCompatActivity {
    TextView txtCreateAccount;
    Button btnLogin;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setOnClickButtonLogin();
        (new ChangeColorSttBar(this)).setForSttBar();
        setClickForCreateAccount();
    }

    private void setOnClickButtonLogin() {
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    private void setClickForCreateAccount() {
        txtCreateAccount = findViewById(R.id.txtCreateAccount);
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
