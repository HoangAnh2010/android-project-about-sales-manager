package com.example.btl.activity.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.Adapter.SanPhamAdapter;
import com.example.btl.activity.Database.DBContext;
import com.example.btl.activity.Entity.CTHoaDon;
import com.example.btl.activity.Entity.SanPham;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    TextInputEditText edtGiaBan, edtSoLuong;
    Spinner spTenSP;
    Button btnAdd;
    TextView tvThanhTien, tvTongTien;
    ListView lvCTHD;
    ImageButton imgBack;
    DBContext dbContext;
    List<CTHoaDon> ctHoaDonList;
    ArrayAdapter<CTHoaDon> adapter;
    SanPhamAdapter ad;
    int maHoaDon;
    SanPham sanPham;
    CTHoaDon ctHoaDon;
    List<SanPham> sanPhamList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiethd);
        dbContext = new DBContext(this);
        init();
        loadData();
        refresh();
        onClick();
    }

    private void refresh() {
        edtGiaBan.setText("");
        edtSoLuong.setText("");
        tvThanhTien.setText("");
    }

    private void loadData() {
        maHoaDon = getIntent().getIntExtra("maHD", 0);
        ctHoaDonList = dbContext.getCTHoaDon(maHoaDon);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, ctHoaDonList);
        lvCTHD.setAdapter(adapter);
        sanPhamList = dbContext.getAllSanPhams();
        ad = new SanPhamAdapter(this, sanPhamList);
        spTenSP.setAdapter(ad);
        tvTongTien.setText(tongTien() + "");
    }

    private int tongTien() {
        int tong = 0;
        for (CTHoaDon ctHoaDon : ctHoaDonList) {
            tong += ctHoaDon.getThanhTien();
        }
        return tong;
    }

    private void onClick() {
        spTenSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sanPham = sanPhamList.get(i);
                edtGiaBan.setText(String.valueOf(sanPham.getGiaBan()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sanPham = sanPhamList.get(0);
            }
        });

        lvCTHD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ctHoaDon = ctHoaDonList.get(position);

                edtGiaBan.setText(String.valueOf(ctHoaDon.getGiaBan()));
                edtSoLuong.setText(String.valueOf(ctHoaDon.getSoLuong()));
                tvThanhTien.setText(String.valueOf(ctHoaDon.getThanhTien()));
            }
        });
        lvCTHD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (sanPham != null) {
                    AlertDialog.Builder al = new AlertDialog.Builder(ChiTietHoaDonActivity.this);
                    al.setTitle("Bạn có chắc chắn muốn xóa không?");
                    al.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dbContext.deleteChiTietHD(ctHoaDon.getMaCTHD());

                            refresh();
                            loadData();
                        }
                    });
                    al.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    al.create().show();

                } else {
                    Toast.makeText(getApplicationContext(), "Chưa chọn chi tiết hóa đơn cần xóa", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        edtSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().equals("")) {
                    int soluong = Integer.parseInt(s.toString());
                    int giaban = Integer.parseInt(edtGiaBan.getText().toString());
                    tvThanhTien.setText(String.valueOf(soluong * giaban));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        }

        );
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtSoLuong.getText().toString().equals("")) {
                    if(Integer.parseInt(edtSoLuong.getText().toString())<=0) {
                        Toast.makeText(ChiTietHoaDonActivity.this, "Số lượng nhập vào không hợp lệ", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (Integer.parseInt(edtSoLuong.getText().toString()) > sanPham.getSoLuong()) {
                            Toast.makeText(ChiTietHoaDonActivity.this, "Không đủ sản phẩm để bán", Toast.LENGTH_LONG).show();
                        } else {
                            CTHoaDon ctHoaDon = dbContext.getCTHDbyID(sanPham.getMaSP(), maHoaDon);
                            int checkSL = Integer.parseInt(edtSoLuong.getText().toString());
                            if (ctHoaDon != null) {
                                int amount = ctHoaDon.getSoLuong() + Integer.parseInt(edtSoLuong.getText().toString()); //Số lượng trong CTHoaDon đã có + số lượng mới thêm vào;
                                dbContext.addCTHoaDon(maHoaDon, ctHoaDon.getMaCTHD(), sanPham.getMaSP(), Integer.parseInt(edtGiaBan.getText().toString()), amount, true);
                            } else {
                                dbContext.addCTHoaDon(maHoaDon, 0, sanPham.getMaSP(), Integer.parseInt(edtGiaBan.getText().toString()), Integer.parseInt(edtSoLuong.getText().toString()), false);
                            }
                            refresh();

                            loadData();
                        }
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số lượng", Toast.LENGTH_LONG).show();
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), QuanLyHoaDonActivity.class);
                startActivity(in);
            }
        });
    }

    private void init() {
        //dbContext = new DBContext(getApplicationContext());
        spTenSP = findViewById(R.id.spTenSPChiTietHD);
        edtGiaBan = findViewById(R.id.edtGiaBanChiTietHD);
        edtSoLuong = findViewById(R.id.edtSoLuongChiTietHD);
        edtGiaBan.setFocusable(false);

        imgBack = findViewById(R.id.imgBackChiTietHD);

        tvThanhTien = findViewById(R.id.tvThanhTien);
        tvTongTien = findViewById(R.id.tvTongTien);

        btnAdd = findViewById(R.id.btnThemChiTietHD);

        lvCTHD = findViewById(R.id.lvChiTietHD);
    }
}
