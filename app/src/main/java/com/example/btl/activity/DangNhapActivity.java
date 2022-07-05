package com.example.btl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.Database.DBContext;
import com.example.btl.activity.Entity.NhanVien;
import com.google.android.material.textfield.TextInputEditText;

public class DangNhapActivity extends AppCompatActivity {
    TextInputEditText edtuser, edtpass;
    Button btnLogin;
    CheckBox cbHienMK;
    TextView tvDangKy;
    DBContext dbContext;
    NhanVien nv ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        init();
        onClick();
    }

    @Override
    public void onBackPressed() {

    }

    public void onClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtuser.getText().toString();

                if(username != null){
                    nv = dbContext.getNhanVienByTenDN(username);
                }
                if (nv != null && nv.getMatKhau().equals(edtpass.getText().toString())){
                    Intent i = new Intent(view.getContext(), MainActivity.class);
                    nv.setMatKhau("");
                    i.putExtra("nv",nv);
                    startActivity(i);
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                }
            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), DangKyActivity.class);
                startActivity(i);
            }
        });
        cbHienMK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    edtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    edtpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    private void init() {
        dbContext = new DBContext(this);
        edtuser= findViewById(R.id.edtTenDNDangNhap);
        edtpass= findViewById(R.id.edtMKDangNhap);
        btnLogin= findViewById(R.id.btnDangNhapDN);
        tvDangKy=findViewById(R.id.tvDangkyDN);
        cbHienMK=findViewById(R.id.cbHienMK);
    }
}
