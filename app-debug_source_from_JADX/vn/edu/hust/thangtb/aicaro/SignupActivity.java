package vn.edu.hust.thangtb.aicaro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    Button btnSignUp;
    TextView txtEmail;
    TextView txtPassword;
    TextView txtUsername;

    class C03191 implements OnClickListener {
        C03191() {
        }

        public void onClick(View v) {
            String email = SignupActivity.this.txtEmail.getText().toString().trim();
            String username = SignupActivity.this.txtUsername.getText().toString().trim();
            String password = SignupActivity.this.txtPassword.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(SignupActivity.this.getApplicationContext(), "Enter email address!", 0).show();
            } else if (TextUtils.isEmpty(username)) {
                Toast.makeText(SignupActivity.this.getApplicationContext(), "Enter username!", 0).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(SignupActivity.this.getApplicationContext(), "Enter password!", 0).show();
            } else if (password.length() < 6) {
                Toast.makeText(SignupActivity.this.getApplicationContext(), "Password too short, enter minimum 6 characters!", 0).show();
            } else {
                if (SignupActivity.this.SaveAccount(username, email, password)) {
                    SignupActivity.this.MoveToActivity(SetupActivity.class, new String[0]);
                }
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0317R.layout.activity_signup);
        SignUp();
    }

    public void SignUp() {
        this.txtUsername = (TextView) findViewById(C0317R.id.username);
        this.txtPassword = (TextView) findViewById(C0317R.id.password);
        this.txtEmail = (TextView) findViewById(C0317R.id.email);
        this.btnSignUp = (Button) findViewById(C0317R.id.btnSignUp);
        this.btnSignUp.setOnClickListener(new C03191());
    }

    public boolean SaveAccount(String... s) {
        return true;
    }

    public void MoveToActivity(Class<?> cls, String... s) {
        Intent intent = new Intent(this, cls);
        if (s.length >= 2) {
            intent.putExtra(s[0], s[1]);
        }
        startActivity(intent);
    }
}
