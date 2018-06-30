package vn.edu.hust.thangtb.aicaro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SetupActivity extends AppCompatActivity {
    private Button btnGo;
    private boolean checkFP = false;
    private boolean checkLevel = false;
    private boolean checkXO = false;
    private int firstPlayer = 0;
    private int level = 1;
    private RadioButton rbAI;
    private RadioButton rbEasy;
    private RadioButton rbHard;
    private RadioButton rbMedium;
    private RadioButton rbO;
    private RadioButton rbP;
    private RadioButton rbX;
    private int shape = 0;

    class C03181 implements OnClickListener {
        C03181() {
        }

        public void onClick(View view) {
            if (SetupActivity.this.rbX.isChecked() || SetupActivity.this.rbO.isChecked()) {
                SetupActivity.this.checkXO = true;
                if (SetupActivity.this.rbX.isChecked()) {
                    SetupActivity.this.shape = 3;
                } else {
                    SetupActivity.this.shape = 4;
                }
            }
            if (SetupActivity.this.rbP.isChecked() || SetupActivity.this.rbAI.isChecked()) {
                SetupActivity.this.checkFP = true;
                if (SetupActivity.this.rbAI.isChecked()) {
                    SetupActivity.this.firstPlayer = 0;
                } else {
                    SetupActivity.this.firstPlayer = 1;
                }
            }
            if (SetupActivity.this.rbEasy.isChecked() || SetupActivity.this.rbMedium.isChecked() || SetupActivity.this.rbHard.isChecked()) {
                SetupActivity.this.checkLevel = true;
                if (SetupActivity.this.rbEasy.isChecked()) {
                    SetupActivity.this.level = 3;
                } else if (SetupActivity.this.rbMedium.isChecked()) {
                    SetupActivity.this.level = 4;
                } else if (SetupActivity.this.rbHard.isChecked()) {
                    SetupActivity.this.level = 5;
                }
            }
            Toast.makeText(SetupActivity.this, SetupActivity.this.checkFP + "  " + SetupActivity.this.checkXO + " " + SetupActivity.this.checkLevel, 0).show();
            if (SetupActivity.this.checkXO && SetupActivity.this.checkFP && SetupActivity.this.checkLevel) {
                SetupActivity.this.MoveToActivity(MainActivity.class, "fistPlayer", SetupActivity.this.firstPlayer + "", "shape", SetupActivity.this.shape + "", "level", SetupActivity.this.level + "");
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0317R.layout.activity_setup);
        this.rbX = (RadioButton) findViewById(C0317R.id.rbX);
        this.rbO = (RadioButton) findViewById(C0317R.id.rbO);
        this.rbP = (RadioButton) findViewById(C0317R.id.rbP);
        this.rbAI = (RadioButton) findViewById(C0317R.id.rbAI);
        this.rbEasy = (RadioButton) findViewById(C0317R.id.rbEasy);
        this.rbHard = (RadioButton) findViewById(C0317R.id.rbHard);
        this.rbMedium = (RadioButton) findViewById(C0317R.id.rbMedium);
        this.btnGo = (Button) findViewById(C0317R.id.btnGo);
        this.btnGo.setOnClickListener(new C03181());
    }

    public void MoveToActivity(Class<?> cls, String... s) {
        Intent intent = new Intent(this, cls);
        Bundle bundle = new Bundle();
        if (s.length >= 2) {
            bundle.putString(s[0], s[1]);
        }
        if (s.length >= 4) {
            bundle.putString(s[2], s[3]);
        }
        if (s.length >= 6) {
            bundle.putString(s[4], s[5]);
        }
        intent.putExtra("SetUp", bundle);
        startActivity(intent);
    }
}
