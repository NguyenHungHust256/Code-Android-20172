package com.example.bahung.asyntask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khởi tạo tiến trình của bạn
                //Truyền Activity chính là MainActivity sang bên tiến trình của mình
                myAsyncTask = new MyAsyncTask(MainActivity.this);
                //Gọi hàm execute để kích hoạt tiến trình
                myAsyncTask.execute();
            }
        });
    }
}
