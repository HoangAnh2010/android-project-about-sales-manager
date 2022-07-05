package com.example.btl.activity.Entity;

public class SanPham {
    private int maSP, giaNhap, giaBan, soLuong;
    private String tenSP;
    private String tenLoai;

    public SanPham(){
    }
    public SanPham(String tenSP){
        this.tenSP = tenSP;
    }

    public SanPham(int maSP, String tenSP,String tenLoai,int soLuong,int giaNhap, int giaBan ) {
        this.maSP = maSP;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.tenLoai = tenLoai;
    }


    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
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

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

//    @Override
//    public String toString() {
//        return String.format(" Tên sản phẩm: %s \n Tên loại: %s \n Giá nhập: %s VNĐ \n Giá bán: %s VNĐ \n Số lượng: %s \n Mã loại: %s", tenSP, tenLoai, giaNhap, giaBan, soLuong);
//    }
}
