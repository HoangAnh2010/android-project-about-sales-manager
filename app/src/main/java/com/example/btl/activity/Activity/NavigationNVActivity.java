package com.example.btl.activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.DangNhapActivity;
import com.example.btl.activity.ThongKeActivity;

public class NavigationNVActivity extends AppCompatActivity {
    TextView tvloaisp, tvsp, tvtk, tvhd, tvdangxuat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_menu_nv);
        Init();
        xulySK();
    }

    private void xulySK() {
        tvdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), DangNhapActivity.class);
                startActivity(i);
            }
        });

        tvsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), QuanLySanPhamActivity.class);
                startActivity(i);
            }
        });
        tvhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), QuanLyHoaDonActivity.class);
                startActivity(i);
            }
        });
        tvtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), ThongKeActivity.class);
                startActivity(i);
            }
        });
    }

    private void Init() {
        tvsp=findViewById(R.id.tvQLSPnv);
        tvtk=findViewById(R.id.tvThongKenv);
        tvdangxuat=findViewById(R.id.tvSignoutnv);
        tvhd=findViewById(R.id.tvQLHoaDonnv);
    }
}
