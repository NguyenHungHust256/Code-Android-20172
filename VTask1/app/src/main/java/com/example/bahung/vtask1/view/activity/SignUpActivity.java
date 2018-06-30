package com.example.bahung.vtask1.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bahung.vtask1.R;
import com.example.bahung.vtask1.utils.ChangeColorSttBar;

public class SignUpActivity extends AppCompatActivity {
    String arrQuyMo[] = {"Quy mô (*)", "Tổ chức (0 - 10 người)", "Tổ chức (11 - 20 người)", "Tổ chức (21 - 50 người)", "Tổ chức (51 - 100 người)", "Trên 100 người"};
    String arrPlace[] = {"Tỉnh thành (*)", "Hà Nội", "TP. Hồ Chí Minh", "Đà Nẵng","Tỉnh thành khác"};
    Spinner spnQuyMo, spnAddress;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        (new ChangeColorSttBar(this)).setForSttBar();
        setEventForSpinnerQuyMo();
        setEventForSpinnerPlace();
    }

    private void setEventForSpinnerPlace() {
        spnAddress = findViewById(R.id.spnAddress);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arrPlace
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spnAddress.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spnAddress.setOnItemSelectedListener(new MyProcessEvent(spnAddress));
    }

    private void setEventForSpinnerQuyMo() {
        spnQuyMo = findViewById(R.id.spnQuyMo);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arrQuyMo
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spnQuyMo.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spnQuyMo.setOnItemSelectedListener(new MyProcessEvent(spnQuyMo));
    }

    //Class tạo sự kiện
    private class MyProcessEvent implements
            AdapterView.OnItemSelectedListener {
        Spinner spn;

        public MyProcessEvent(Spinner spn) {
            this.spn = spn;
        }
        //Khi có chọn lựa thì vào hàm

        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            spn.setSelection(arg2);
        }

        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            spn.setSelection(0);
        }
    }

}
