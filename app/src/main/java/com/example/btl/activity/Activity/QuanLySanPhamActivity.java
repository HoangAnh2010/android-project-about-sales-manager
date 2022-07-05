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
import com.example.btl.activity.Adapter.SanPhamAdapter;
import com.example.btl.activity.Database.DBContext;
import com.example.btl.activity.Entity.SanPham;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class QuanLySanPhamActivity extends AppCompatActivity {

    AutoCompleteTextView edtKey;
    ImageButton imgSearch, imgHome;
    TextInputEditText edtTenLoai, edtTenSP, edtSoLg, edtGiaNhap, edtGiaBan;
    Button btnAdd, btnEdit, btnDel;
    ListView lvqlsp;
    ArrayAdapter<SanPham> adapter;
    DBContext dbContext;
    List<SanPham> sanPhamList;
    SanPham sanPham;
    SanPhamAdapter ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlysanpham);
        dbContext = new DBContext(this);
        init();
        loadData();
        refresh();
        onClick();
    }

    private void refresh() {
        edtTenSP.setText("");
        edtTenLoai.setText("");
        edtGiaNhap.setText("");
        edtGiaBan.setText("");
        edtSoLg.setText("");
    }

    private void init() {
        lvqlsp = findViewById(R.id.lvqlsp);

        imgSearch = findViewById(R.id.imgSearchSP);
        imgHome = findViewById(R.id.imgHomeQLSP);

        edtKey = findViewById(R.id.edtKeySP);
        edtTenLoai = findViewById(R.id.edtTenLoaiQLSP);
        edtTenSP = findViewById(R.id.edtTenSPQLSP);
        edtGiaNhap = findViewById(R.id.edtGiaNhapQLSP);
        edtGiaBan = findViewById(R.id.edtGiaBanQLSP);
        edtSoLg = findViewById(R.id.edtSoLuongQLSP);

        btnAdd = findViewById(R.id.btnThemSP);
        btnEdit = findViewById(R.id.btnSuaSP);
        btnDel = findViewById(R.id.btnXoaSP);

    }

    private void loadData() {
        sanPhamList = dbContext.getAllSanPhams();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sanPhamList);
        ad = new SanPhamAdapter(this,sanPhamList);
        lvqlsp.setAdapter(ad);
//        edtKey.setAdapter(adapter);
    }

    private void onClick() {
        lvqlsp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sanPham = sanPhamList.get(i);
                edtTenSP.setText(sanPham.getTenSP());
                edtTenLoai.setText(sanPham.getTenLoai());
                edtSoLg.setText(sanPham.getSoLuong()+"");
                edtGiaNhap.setText(sanPham.getGiaNhap()+"");
                edtGiaBan.setText(sanPham.getGiaBan()+"");

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(edtSoLg.getText().toString())<=0 || Integer.parseInt(edtGiaNhap.getText().toString())<=0 || Integer.parseInt(edtGiaBan.getText().toString())<=0){
                    Toast.makeText(QuanLySanPhamActivity.this, "Dữ liệu nhập vào không hợp lệ", Toast.LENGTH_SHORT).show();
                }else {
                    SanPham sanPham = getSanPham();
                    dbContext.add_edit_SanPham(sanPham, false);

                    refresh();
                    loadData();
                }
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sanPham != null) {
                    SanPham sp =  getSanPham();
                    sp.setMaSP(sanPham.getMaSP());
                    dbContext.add_edit_SanPham(sp,true);

                    refresh();
                    loadData();
                } else {
                    Toast.makeText(getApplicationContext(), "Chưa chọn sản phẩm cần sửa", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sanPham != null) {
                    AlertDialog.Builder al=new AlertDialog.Builder(QuanLySanPhamActivity.this);
                    al.setTitle("Bạn có chắc chắn muốn xóa không?");
                    al.setPositiveButton( "Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dbContext.deleteSanPham(sanPham.getMaSP());
                            refresh();
                            loadData();
                        }
                    } );
                    al.setNegativeButton( "Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    } );

                    al.create().show();

                } else {
                    Toast.makeText(getApplicationContext(), "Chưa chọn sản phẩm cần xóa", Toast.LENGTH_LONG).show();
                }
            }
        });
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(view.getContext(), NavigationMenuActivity.class);
                startActivity(i);
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String txtSearch = edtKey.getText().toString();
                if(!txtSearch.trim().equals("")){
                    sanPhamList = dbContext.getAllSanPhamByName(txtSearch);
                    ad = new SanPhamAdapter(QuanLySanPhamActivity.this,sanPhamList);
                    lvqlsp.setAdapter(ad);
                }
            }
        });
    }
    private SanPham getSanPham() {
        SanPham sp = new SanPham();
        sp.setTenSP(edtTenSP.getText().toString());
        sp.setTenLoai(edtTenLoai.getText().toString());
        sp.setGiaBan(Integer.parseInt(edtGiaBan.getText().toString().trim()));
        sp.setGiaNhap(Integer.parseInt(edtGiaNhap.getText().toString().trim()));
        sp.setSoLuong(Integer.parseInt(edtSoLg.getText().toString().trim()));
        return sp;
    }
}
