package com.example.btl.activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.activity.Activity.QuanLyNhanVienActivity;
import com.example.btl.activity.Activity.QuanLySanPhamActivity;
import com.example.btl.activity.Entity.NhanVien;
import com.example.btl.activity.Entity.SanPham;

import java.util.List;

public class SanPhamAdapter extends BaseAdapter {

    private Context context;
    private List<SanPham> sanPhamList;

    public SanPhamAdapter(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int position) {
        return sanPhamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.sanpham, null);
        TextView text = (TextView) view.findViewById(R.id.tvtensp);
        text.setText(sanPhamList.get(position).getTenSP());

        return view;
    }
}
