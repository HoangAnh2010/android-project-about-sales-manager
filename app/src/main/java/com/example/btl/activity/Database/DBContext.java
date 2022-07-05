package com.example.btl.activity.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.btl.activity.Activity.ChiTietHoaDonActivity;
import com.example.btl.activity.Activity.QuanLyHoaDonActivity;
import com.example.btl.activity.Activity.QuanLyNhanVienActivity;
import com.example.btl.activity.Activity.QuanLySanPhamActivity;
import com.example.btl.activity.Entity.CTHoaDon;
import com.example.btl.activity.Entity.HoaDon;
import com.example.btl.activity.Entity.NhanVien;
import com.example.btl.activity.Entity.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DBContext extends SQLiteOpenHelper {
    SQLiteDatabase database;
    Context context;

    public DBContext(@Nullable Context context) {
        super(context, "oriole.sqlite", null, 1);
        database = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table SanPham(" +
                "maSP integer primary key autoincrement," +
                "tenSP nvarchar(50)," +
                "tenLoai nvarchar(50)," +
                "soLuong integer," +
                "giaNhap integer," +
                "giaBan integer)");
        sqLiteDatabase.execSQL("create table NhanVien(" +
                "maNV integer primary key autoincrement," +
                "tenDN nvarchar(10)," +
                "hoTen nvarchar(50)," +
                "matKhau nvarchar(10)," +
                "email nvarchar(50)," +
                "sdt nvarchar(10)," +
                "diaChi nvarchar(100)," +
                "id_role integer)");
        sqLiteDatabase.execSQL("create table HoaDon(" +
                "maHD integer primary key autoincrement," +
                "tenKH nvarchar(100)," +
                "ngayBan nvarchar(50))");
        sqLiteDatabase.execSQL("create table CTHoaDon(" +
                "maCTHD integer primary key autoincrement," +
                "maHD integer," +
                "maSP integer," +
                "giaBan integer," +
                "soLuong integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<SanPham> getAllSanPhams() {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from SanPham", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }

    public List<SanPham> getAllSanPhamByName(String txtSearch) {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from SanPham where tenSP like ?", new String[]{"%" + txtSearch + "%"});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }

    public SanPham getSPbyID(int masp) {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from SanPham where maSP = " + masp, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        if (sanPhamList.size() > 0) {
            return sanPhamList.get(0);
        }
        return null;
    }

    public void add_edit_SanPham(SanPham sanPham, boolean isUpdate) {
        ContentValues values = new ContentValues();

        values.put("tenSP", sanPham.getTenSP());
        values.put("tenLoai", sanPham.getTenLoai());
        values.put("soLuong", sanPham.getSoLuong());
        values.put("giaNhap", sanPham.getGiaNhap());
        values.put("giaBan", sanPham.getGiaBan());

        if (isUpdate) {
            values.put("maSP", sanPham.getMaSP());
            database.update("SanPham", values, "maSP = ?", new String[]{sanPham.getMaSP() + ""});
            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
        } else {
            database.insert("SanPham", null, values);
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteSanPham(int id) {
        SanPham sanPham = getSPbyID(id);

        if (sanPham == null) {
            Toast.makeText(context, "Sản phẩm không tồn tại trong hệ thống", Toast.LENGTH_LONG).show();
        } else {
            database.delete("SanPham", "maSP= ?", new String[]{id + ""});
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
        }
    }

    public List<HoaDon> getAllHoaDons() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from HoaDon", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HoaDon hoaDon = new HoaDon(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            hoaDonList.add(hoaDon);
            cursor.moveToNext();
        }
        return hoaDonList;
    }

    public void addHoaDon(HoaDon hoaDon, boolean isUpdate) {
        ContentValues values = new ContentValues();
        values.put("tenKH", hoaDon.getTenKH());
        values.put("ngayBan", hoaDon.getNgayBan());

        database.insert("HoaDon", null, values);
        Toast.makeText(context, "Thêm hóa đơn thành công", Toast.LENGTH_LONG).show();

    }

    public HoaDon getHDbyID(int mahd) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from HoaDon where maHD = " + mahd, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HoaDon hoaDon = new HoaDon(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            hoaDonList.add(hoaDon);
            cursor.moveToNext();
        }
        if (hoaDonList.size() > 0) {
            return hoaDonList.get(0);
        }
        return null;
    }

    public void deleteHoaDon(int id) {
        HoaDon hoaDon = getHDbyID(id);

        if (hoaDon == null) {
            Toast.makeText(context, "Hóa đơn không tồn tại trong hệ thống", Toast.LENGTH_LONG).show();
        } else {
            database.delete("HoaDon", "maHD= ?", new String[]{id + ""});
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
        }
    }

    public List<HoaDon> getAllHoaDonByTenKH(String txtSearch) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from HoaDon where tenKH like ?", new String[]{"%" + txtSearch + "%"});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HoaDon hoaDon = new HoaDon(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            hoaDonList.add(hoaDon);
            cursor.moveToNext();
        }
        return hoaDonList;
    }

    public List<CTHoaDon> getCTHoaDon(int idHoaDon) {
        List<CTHoaDon> ctHoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select CTHoaDon.maCTHD,CTHoaDon.maHD,CTHoaDon.maSP,SanPham.tenSP,SanPham.giaBan,CTHoaDon.soLuong,SanPham.maSP from CTHoaDon,SanPham where SanPham.maSP = CTHoaDon.maSP and CTHoaDon.maHD = " + idHoaDon, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CTHoaDon ctHoaDon = new CTHoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(6), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
            ctHoaDonList.add(ctHoaDon);
            cursor.moveToNext();
        }
        return ctHoaDonList;
    }

    public void addCTHoaDon(int maHD, int maCTHD, int maSP, int giaBan, int soLuong, boolean isUpdate) {
        ContentValues values = new ContentValues();

        if (isUpdate) {
            values.put("soLuong", soLuong);
            values.put("maCTHD", maCTHD);

            database.update("CTHoaDon", values, "maCTHD = " + maCTHD, null);
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        } else {
            values.put("maHD", maHD);
            values.put("maSP", maSP);
            values.put("giaBan", giaBan);
            values.put("soLuong", soLuong);
            database.insert("CTHoaDon", null, values);
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }

        //cập nhật số lượng sp trong kho

        SanPham sanPham = getSPbyID(maSP);
        if (sanPham != null) {
            int soLgUpdate = sanPham.getSoLuong() - soLuong;
            ContentValues valueSP = new ContentValues();
            valueSP.put("soLuong", soLgUpdate);
            database.update("SanPham", valueSP, "maSP = " + sanPham.getMaSP(), null);
        }
    }

    public boolean check(int idSP) {
        boolean flag = false;
        for (CTHoaDon ctHoaDon : getAllCTHoaDon()) {
            if (idSP == ctHoaDon.getMaSP()) flag = true;
        }
        return flag;
    }

    public void deleteChiTietHD(int id) {
        if (check(id)) {
            Toast.makeText(context, "Chi tiết hóa đơn không tồn tại trong hệ thống", Toast.LENGTH_LONG).show();
        } else {
            database.delete("CTHoaDon", "maCTHD = ?", new String[]{id + ""});

            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
        }
    }


    public List<CTHoaDon> getAllCTHoaDon() {
        List<CTHoaDon> ctHoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select CTHoaDon.maCTHD,CTHoaDon.maHD,SanPham.tenSP,SanPham.giaBan,CTHoaDon.soLuong,SanPham.maSP from CTHoaDon,SanPham where SanPham.maSP = CTHoaDon.maSP", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CTHoaDon ctHoaDon = new CTHoaDon(cursor.getInt(0), cursor.getInt(1), cursor.getInt(5), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
            ctHoaDonList.add(ctHoaDon);
            cursor.moveToNext();
        }
        return ctHoaDonList;
    }


    public CTHoaDon getCTHDbyID(int masp, int mahd) {
        List<CTHoaDon> ctHoaDonList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from CTHoaDon where maSP = " + masp + " and maHD = " + mahd, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CTHoaDon ctHoaDon = new CTHoaDon();
            ctHoaDon.setMaCTHD(cursor.getInt(0));
            ctHoaDon.setMaHD(cursor.getInt(1));
            ctHoaDon.setMaSP(cursor.getInt(2));
            ctHoaDon.setGiaBan(cursor.getInt(3));
            ctHoaDon.setSoLuong(cursor.getInt(4));
            ctHoaDonList.add(ctHoaDon);
            cursor.moveToNext();
        }
        if (ctHoaDonList.size() > 0) {
            return ctHoaDonList.get(0);
        }
        return null;
    }


    public List<NhanVien> getAllNhanViens() {
        List<NhanVien> nhanVienList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from NhanVien", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
            nhanVienList.add(nhanVien);
            cursor.moveToNext();
        }
        return nhanVienList;
    }

    public List<NhanVien> getAllNhanVienByHoTen(String txtSearch) {
        List<NhanVien> nhanVienList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from NhanVien where hoTen like ?", new String[]{"%" + txtSearch + "%"});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
            nhanVienList.add(nhanVien);
            cursor.moveToNext();
        }
        return nhanVienList;
    }

    public void add_edit_NhanVien(NhanVien nhanVien, boolean isUpdate) {
        ContentValues values = new ContentValues();

        values.put("tenDN", nhanVien.getTenDN());
        values.put("hoTen", nhanVien.getHoTen());
        values.put("matKhau", nhanVien.getMatKhau());
        values.put("email", nhanVien.getEmail());
        values.put("sdt", nhanVien.getSdt());
        values.put("diaChi", nhanVien.getDiaChi());
        values.put("id_role", nhanVien.getId_role());
        if (isUpdate) {
            values.put("tenDN", nhanVien.getTenDN());
            database.update("NhanVien", values, "maNV = ?", new String[]{nhanVien.getMaNV() + ""});
            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
        } else {
            database.insert("NhanVien", null, values);

        }
    }

    public NhanVien getNhanVienbyID(int maNV) {
        List<NhanVien> nhanVienList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from NhanVien where maNV = " + maNV, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
            nhanVienList.add(nhanVien);
            cursor.moveToNext();
        }
        if (nhanVienList.size() > 0) {
            return nhanVienList.get(0);
        }
        return null;
    }

    public void deleteNhanVien(int id) {
        NhanVien nhanVien = getNhanVienbyID(id);

        if (nhanVien == null) {
            Toast.makeText(context, "Nhân viên không tồn tại trong hệ thống", Toast.LENGTH_LONG).show();
        } else {
            database.delete("NhanVien", "maNV= ?", new String[]{id + ""});
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
        }
    }

    public NhanVien getNhanVienByTenDN(String tenDN) {
        if (tenDN != null && !"".equals(tenDN.trim())) {
            List<NhanVien> nhanVienList = new ArrayList<>();
            Cursor cursor = database.rawQuery("select * from NhanVien where tenDN = ?", new String[]{tenDN});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
                nhanVienList.add(nhanVien);
                cursor.moveToNext();
            }
            return nhanVienList.get(0);
        }
        return null;

    }

    public List<SanPham> getSPBanchay() {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select distinct SanPham.* from CTHoaDon,SanPham where SanPham.maSP = CTHoaDon.maSP order by CTHoaDon.soLuong DESC LIMIT 4", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }

    public List<SanPham> getSPTonKho() {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select distinct SanPham.* from CTHoaDon,SanPham where SanPham.maSP = CTHoaDon.maSP order by CTHoaDon.soLuong ASC LIMIT 10", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SanPham sanPham = new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }
}
