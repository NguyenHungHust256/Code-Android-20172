package vn.edu.hust.thangtb.aicaro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    Button btn_login;
    TextView txtPassword;
    TextView txtSignUp;
    TextView txtUsername;

    class C03121 implements OnClickListener {
        C03121() {
        }

        public void onClick(View view) {
            if (LoginActivity.this.CheckAccout(LoginActivity.this.txtUsername.toString(), LoginActivity.this.txtPassword.toString())) {
                LoginActivity.this.MoveToActivity(SetupActivity.class, new String[0]);
            } else {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", 0).show();
            }
        }
    }

    class C03132 implements OnClickListener {
        C03132() {
        }

        public void onClick(View view) {
            LoginActivity.this.MoveToActivity(SignupActivity.class, new String[0]);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0317R.layout.activity_login);
        Login();
    }

    public void Login() {
        this.txtUsername = (TextView) findViewById(C0317R.id.username);
        this.txtPassword = (TextView) findViewById(C0317R.id.password);
        this.btn_login = (Button) findViewById(C0317R.id.btn_login);
        this.txtSignUp = (TextView) findViewById(C0317R.id.txtSignUp);
        this.btn_login.setOnClickListener(new C03121());
        this.txtSignUp.setOnClickListener(new C03132());
    }

    public void MoveToActivity(Class<?> cls, String... s) {
        Intent intent = new Intent(this, cls);
        if (s.length >= 2) {
            intent.putExtra(s[0], s[1]);
        }
        startActivity(intent);
    }

    public boolean CheckAccout(String usr, String pass) {
        return true;
    }
}
