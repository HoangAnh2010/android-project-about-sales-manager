package com.example.btl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.Database.DBContext;
import com.example.btl.activity.Entity.NhanVien;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DangKyActivity extends AppCompatActivity {

    TextInputEditText edtHoTen, edtTenDN, edtMK, edtEmail, edtSDT, edtDC;
    Button btnDangKy, btnHuy;
    ImageButton imgBack;
    DBContext dbContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        dbContext = new DBContext(this);
        init();
        refresh();
        onClick();
    }

    private void refresh() {
        edtTenDN.setText("");
        edtHoTen.setText("");
        edtMK.setText("");
        edtEmail.setText("");
        edtSDT.setText("");
        edtDC.setText("");
    }
    public void onClick() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NhanVien nhanVien = new NhanVien();

                nhanVien.setTenDN(edtTenDN.getText().toString());
                nhanVien.setHoTen(edtHoTen.getText().toString());
                nhanVien.setMatKhau(edtMK.getText().toString());
                nhanVien.setEmail(edtEmail.getText().toString());
                nhanVien.setSdt(edtSDT.getText().toString());
                nhanVien.setDiaChi(edtDC.getText().toString());
                nhanVien.setId_role(1);

                dbContext.add_edit_NhanVien(nhanVien, false);

                refresh();
                Toast.makeText(DangKyActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_LONG).show();
                Intent i = new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(i);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), DangNhapActivity.class);
                startActivity(i);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), DangNhapActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {

        edtHoTen= findViewById(R.id.edtHoTenDangKy);
        edtTenDN= findViewById(R.id.edtTenDNDangKy);
        edtMK= findViewById(R.id.edtMKDangKy);
        edtEmail= findViewById(R.id.edtEmailDangKy);
        edtSDT= findViewById(R.id.edtSDTDangKy);
        edtDC= findViewById(R.id.edtDiachiDangKy);

        imgBack=findViewById(R.id.imgBackDangKy);

        btnDangKy= findViewById(R.id.btnDangky);
        btnHuy=findViewById(R.id.btnHuyDangKy);
    }
}
