package com.example.btl.activity.Entity;

import java.util.List;

public class CTHoaDon {
    private int maCTHD, maHD, maSP, giaBan, soLuong, thanhTien;
    private String tenSP;

    public CTHoaDon(){

    }

    public CTHoaDon(int maCTHD, int maHD, int maSP, String tenSP, int giaBan, int soLuong) {
        this.maCTHD = maCTHD;
        this.maHD = maHD;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public int getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(int maCTHD) {
        this.maCTHD = maCTHD;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return giaBan * soLuong;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = giaBan * soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    @Override
    public String toString() {
        return String.format(" Tên sản phẩm: %s \n Giá bán: %s \n Số lượng: %s \n Thành Tiền: %s VNĐ", tenSP, giaBan, soLuong, getThanhTien());
    }
}
