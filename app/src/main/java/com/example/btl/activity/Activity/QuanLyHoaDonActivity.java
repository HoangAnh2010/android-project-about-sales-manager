package com.example.btl.activity.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.Adapter.HoaDonAdapter;
import com.example.btl.activity.Adapter.SanPhamAdapter;
import com.example.btl.activity.Database.DBContext;
import com.example.btl.activity.Entity.HoaDon;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class QuanLyHoaDonActivity extends AppCompatActivity {
    AutoCompleteTextView edtKey;
    ImageButton imgSearch, imgHome;
    TextInputEditText edtTenKH, edtNgayBan;
    Button btnAdd;
    ListView lvhd;
    DBContext dbContext;
    List<HoaDon> hoaDonList;
    ArrayAdapter<HoaDon> adapter;
    HoaDon hoaDon;
    HoaDonAdapter ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlyhoadon);
        dbContext = new DBContext(getApplicationContext());
        init();
        loadData();
        refresh();
        onClick();
    }

    private void refresh() {
        edtTenKH.setText("");
        edtNgayBan.setText("");
    }

    private void onClick() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HoaDon hoaDon = new HoaDon();
                hoaDon.setTenKH(edtTenKH.getText().toString());
                hoaDon.setNgayBan(edtNgayBan.getText().toString());
                dbContext.addHoaDon(hoaDon, false);

                refresh();
                loadData();

            }
        });

        lvhd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HoaDon hoaDon = hoaDonList.get(i);
                edtTenKH.setText(hoaDon.getTenKH());
                edtNgayBan.setText(hoaDon.getNgayBan());
                Intent intents = new Intent(getApplicationContext(), ChiTietHoaDonActivity.class);
                intents.putExtra("maHD", hoaDon.getMaHD());
                startActivity(intents);
            }
        });

        lvhd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                hoaDon = hoaDonList.get(position);
                if (hoaDon != null) {
                    AlertDialog.Builder al = new AlertDialog.Builder(QuanLyHoaDonActivity.this);
                    al.setTitle("Bạn có chắc chắn muốn xóa không?");
                    al.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int in) {
                            dbContext.deleteHoaDon(hoaDon.getMaHD());
                            refresh();
                            loadData();
                        }
                    });
                    al.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int im) {

                        }
                    });

                    al.create().show();

                } else {
                    Toast.makeText(getApplicationContext(), "Chưa chọn hóa đơn cần xóa", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), NavigationMenuActivity.class);
                startActivity(in);
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtSearch = edtKey.getText().toString();
                if(!txtSearch.trim().equals("")){
                    hoaDonList = dbContext.getAllHoaDonByTenKH(txtSearch);
                    ad = new HoaDonAdapter(QuanLyHoaDonActivity.this,hoaDonList);
                    lvhd.setAdapter(ad);
                }
            }
        });
    }

    private void loadData() {
        hoaDonList = dbContext.getAllHoaDons();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hoaDonList);
        ad = new HoaDonAdapter(this, hoaDonList);
        lvhd.setAdapter(ad);
//        edtKey.setAdapter(adapter);
    }

    private void init() {
        //dbContext = new DBContext(getApplicationContext());
        edtKey = findViewById(R.id.edtKeyHD);
        edtTenKH = findViewById(R.id.edtTenKhachHD);
        edtNgayBan = findViewById(R.id.edtNgayBanHD);

        imgSearch = findViewById(R.id.imgSearchHD);
        imgHome = findViewById(R.id.imgHomeQLHD);

        btnAdd = findViewById(R.id.btnThemHD);

        lvhd = findViewById(R.id.lvqlhd);
    }
}
