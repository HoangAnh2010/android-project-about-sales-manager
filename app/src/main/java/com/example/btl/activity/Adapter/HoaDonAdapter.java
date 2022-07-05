package com.example.btl.activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.activity.Entity.HoaDon;
import com.example.btl.activity.Entity.SanPham;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private List<HoaDon> hoaDonList;

    public HoaDonAdapter(Context context, List<HoaDon> hoaDonList) {
        this.context = context;
        this.hoaDonList = hoaDonList;
    }

    @Override
    public int getCount() {
        return hoaDonList.size();
    }

    @Override
    public Object getItem(int position) {
        return hoaDonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.hoadon, null);
        TextView text = (TextView) view.findViewById(R.id.tvhoadon);
        text.setText(hoaDonList.get(position).getTenKH());

        return view;
    }
}
