package com.example.btl.activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.activity.Entity.CTHoaDon;
import com.example.btl.activity.Entity.SanPham;

import java.util.List;

public class ChiTietHDAdapter extends BaseAdapter {
    private Context context;
    private List<CTHoaDon> CTHDList;

    @Override
    public int getCount() {
        return CTHDList.size();
    }

    @Override
    public Object getItem(int position) {
        return CTHDList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.cthd, null);
        TextView text = (TextView) view.findViewById(R.id.tvcthd);
        text.setText(CTHDList.get(position).getTenSP());

        return view;
    }
}
