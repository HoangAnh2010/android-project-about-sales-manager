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

public class NavigationMenuActivity extends AppCompatActivity {
    TextView  tvsp, tvtk, tvnv,tvhd, tvdangxuat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_menu);
        init();
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
        tvnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), QuanLyNhanVienActivity.class);
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

    private void init() {
        tvsp=findViewById(R.id.tvQLSPmenu);
        tvnv=findViewById(R.id.tvQLNhanVienmenu);
        tvtk=findViewById(R.id.tvThongKemenu);
        tvdangxuat=findViewById(R.id.tvSignoutmenu);
        tvhd=findViewById(R.id.tvQLHoaDonmenu);
    }
}
