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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.activity.Adapter.NhanVienAdapter;
import com.example.btl.activity.Adapter.SanPhamAdapter;
import com.example.btl.activity.Database.DBContext;
import com.example.btl.activity.Entity.NhanVien;
import com.example.btl.activity.Entity.SanPham;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class QuanLyNhanVienActivity extends AppCompatActivity {
    TextInputEditText edtHoTen, edtTenDN, edtMK, edtEmail, edtSDT, edtDC;
    AutoCompleteTextView edtKey;
    Button btnAdd, btnEdit, btnDel;
    ImageButton imgHome, imgSearch;
    RadioGroup rbRole;
    RadioButton rbQuanLy, rbNV,rb;
    DBContext dbContext;
    ListView lvqlnv;
    ArrayAdapter<NhanVien> adapter;
    List<NhanVien> nhanVienList;
    NhanVien nhanVien;
    NhanVienAdapter nvAd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlynhanvien);
        dbContext = new DBContext(this);
        init();
        loadData();
        refresh();
        onClick();
    }

    private void loadData() {
        nhanVienList = dbContext.getAllNhanViens();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nhanVienList);
        nvAd = new NhanVienAdapter(this,nhanVienList);
        lvqlnv.setAdapter(nvAd);
    }

    private void onClick() {
        lvqlnv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nhanVien = nhanVienList.get(position);
                edtHoTen.setText(nhanVien.getHoTen());
                edtTenDN.setText(nhanVien.getTenDN());
                edtMK.setText(nhanVien.getMatKhau());
                edtEmail.setText(nhanVien.getEmail());
                edtSDT.setText(nhanVien.getSdt());
                edtDC.setText(nhanVien.getDiaChi());
                int role=nhanVien.getId_role();
                if(role==1){
                    rbNV.setChecked(true);
                }
                if(role==2)
                {
                    rbQuanLy.setChecked(true);
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = getNhanVien();
                dbContext.add_edit_NhanVien(nhanVien,false);
                Toast.makeText(QuanLyNhanVienActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                refresh();
                loadData();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nhanVien != null) {
                    NhanVien nv= getNhanVien();
                    nv.setMaNV(nhanVien.getMaNV());
                    dbContext.add_edit_NhanVien(nv,true);

                    refresh();
                    loadData();
                } else {
                    Toast.makeText(getApplicationContext(), "Chưa chọn nhân viên cần sửa", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nhanVien != null) {
                    AlertDialog.Builder al=new AlertDialog.Builder(QuanLyNhanVienActivity.this);
                    al.setTitle("Bạn có chắc chắn muốn xóa không?");
                    al.setPositiveButton( "Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dbContext.deleteNhanVien(nhanVien.getMaNV());

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
                    Toast.makeText(getApplicationContext(), "Chưa chọn nhân viên cần xóa", Toast.LENGTH_LONG).show();
                }
            }
        });
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(v.getContext(), NavigationMenuActivity.class);
                startActivity(i);
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtSearch = edtKey.getText().toString();
                if(!txtSearch.trim().equals("")){
                    nhanVienList = dbContext.getAllNhanVienByHoTen(txtSearch);
                    nvAd = new NhanVienAdapter(QuanLyNhanVienActivity.this,nhanVienList);
                    lvqlnv.setAdapter(nvAd);
                }
            }
        });
    }

    private NhanVien getNhanVien() {
        NhanVien nv = new NhanVien();
        nv.setTenDN(edtTenDN.getText().toString());
        nv.setHoTen(edtHoTen.getText().toString());
        nv.setMatKhau(edtMK.getText().toString());
        nv.setEmail(edtEmail.getText().toString());
        nv.setSdt(edtSDT.getText().toString());
        nv.setDiaChi(edtDC.getText().toString());
        int selectedId = rbRole.getCheckedRadioButtonId();
        int role = 0;
        switch (selectedId){
            case R.id.rbQuanlyQLNV:
                role = 2;
                break;
            case R.id.rbNhanvienQLNV:
                role = 1;
                break;
        }
        rb = (RadioButton) findViewById(selectedId);
        nv.setId_role(role);

        return nv;
    }

    private void refresh() {
        rbQuanLy.setChecked(false);
        rbNV.setChecked(false);
        edtTenDN.setText("");
        edtHoTen.setText("");
        edtMK.setText("");
        edtEmail.setText("");
        edtSDT.setText("");
        edtDC.setText("");
    }

    private void init() {

        edtHoTen= findViewById(R.id.edtHoTenQLNV);
        edtTenDN= findViewById(R.id.edtTenDNQLNV);
        edtMK= findViewById(R.id.edtMKQLNV);
        edtEmail= findViewById(R.id.edtEmailQLNV);
        edtSDT= findViewById(R.id.edtSDTQLNV);
        edtDC= findViewById(R.id.edtDiachiQLNV);
        edtKey=findViewById(R.id.edtKeyNV);

        rbRole=findViewById(R.id.rbQuyenQLNV);
        rbQuanLy=findViewById(R.id.rbQuanlyQLNV);
        rbNV=findViewById(R.id.rbNhanvienQLNV);

        imgHome=findViewById(R.id.imgHomeQLNV);
        imgSearch=findViewById(R.id.imgSearchNV);

        btnAdd= findViewById(R.id.btnThemNV);
        btnEdit=findViewById(R.id.btnSuaNV);
        btnDel=findViewById(R.id.btnXoaNV);

        lvqlnv=findViewById(R.id.lvqlnv);

    }
}
