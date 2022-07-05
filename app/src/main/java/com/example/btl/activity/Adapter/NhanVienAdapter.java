package com.example.btl.activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.activity.Entity.NhanVien;
import com.example.btl.activity.Entity.SanPham;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private List<NhanVien> nhanVienList;

    public NhanVienAdapter(Context context, List<NhanVien> nhanVienList) {
        this.context = context;
        this.nhanVienList = nhanVienList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.nhanvien, null);
        TextView text = (TextView) view.findViewById(R.id.tvtennv);
        text.setText(nhanVienList.get(position).getHoTen());
        return view;
    }
}
