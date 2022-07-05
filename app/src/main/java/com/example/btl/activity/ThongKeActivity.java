package com.example.btl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.Activity.NavigationMenuActivity;
import com.example.btl.activity.Adapter.SanPhamAdapter;
import com.example.btl.activity.Database.DBContext;
import com.example.btl.activity.Entity.SanPham;

import java.util.List;

public class ThongKeActivity extends AppCompatActivity {
    RadioGroup rbThongKe;
    RadioButton rbBanChay, rbTonKho;
    ImageButton imgHome;
    ListView lvThongKe;
    DBContext dbContext;
    SanPhamAdapter ad;
    List<SanPham> sanPhamList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongke);
        dbContext = new DBContext(this);
        init();
        onClick();
    }

    private void loadBanchay(){
        sanPhamList = dbContext.getSPBanchay();
        ad = new SanPhamAdapter(this, sanPhamList);
        lvThongKe.setAdapter(ad);
    }

    private void loadTonkho(){
        sanPhamList = dbContext.getSPTonKho();
        ad = new SanPhamAdapter(this, sanPhamList);
        lvThongKe.setAdapter(ad);
    }
    private void onClick() {
        rbBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBanchay();
            }
        });
        rbTonKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTonkho();
            }
        });
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NavigationMenuActivity.class);
                startActivity(i);
            }
        });
    }

    private void init() {
        imgHome = findViewById(R.id.imgHomeThongKe);

        rbThongKe = findViewById(R.id.rbThongKe);
        rbBanChay = findViewById(R.id.rbBanChay);
        rbTonKho = findViewById(R.id.rbTonKho);

        lvThongKe = findViewById(R.id.lvThongKe);
    }
}
