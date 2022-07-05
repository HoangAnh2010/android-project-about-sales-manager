package com.example.btl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.btl.R;
import com.example.btl.activity.Activity.NavigationMenuActivity;
import com.example.btl.activity.Activity.NavigationNVActivity;
import com.example.btl.activity.Entity.NhanVien;

public class MainActivity extends AppCompatActivity {
    ImageButton imgMenu;
    NhanVien nhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClick();

    }

    private void onClick() {
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                if (nhanVien.getId_role() == 1) {
                    i = new Intent(MainActivity.this, NavigationNVActivity.class);
                    startActivity(i);
                }
                if (nhanVien.getId_role() == 2) {
                    i = new Intent(MainActivity.this, NavigationMenuActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void init() {
        Intent i = getIntent();
        nhanVien = (NhanVien) i.getSerializableExtra("nv");
        imgMenu = findViewById(R.id.menu);
    }
}