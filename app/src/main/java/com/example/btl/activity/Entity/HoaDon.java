package com.example.btl.activity.Entity;

public class HoaDon {
    private int maHD;
    private String tenKH, ngayBan;

    public HoaDon(int maHD, String tenKH, String ngayBan) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.ngayBan = ngayBan;
    }
    public HoaDon(){

    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }
    @Override
    public String toString() {
        return String.format(" Tên khách hàng: %s \n Ngày bán: %s", tenKH, ngayBan);
    }
}
