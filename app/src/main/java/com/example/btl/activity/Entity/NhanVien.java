package com.example.btl.activity.Entity;

import java.io.Serializable;

public class NhanVien  implements Serializable {
    private String tenDN, hoTen, matKhau, email, sdt, diaChi;
    private int maNV,id_role;
    public NhanVien(){
    }

    public NhanVien(int maNV,String tenDN, String hoTen, String matKhau, String email, String sdt, String diaChi, int id_role) {
        this.maNV = maNV;
        this.tenDN = tenDN;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.id_role = id_role;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
    @Override
    public String toString() {
        return String.format(" Tên đăng nhập: %s \n Họ tên: %s \n Mật khẩu: %s \n Email: %s \n Số điện thoại: %s \n Địa chỉ: %s", tenDN, hoTen, matKhau, email, sdt, diaChi);
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
}
