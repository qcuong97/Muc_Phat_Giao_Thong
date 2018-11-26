package com.qc.tra_cuu_vi_pham;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qc.tra_cuu_vi_pham.database.Loi_vi_pham;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Loi_vi_pham> {
    private Context context;
    private int resource;
    private List<Loi_vi_pham> arrLoivipham;
    public CustomAdapter(Context context, int resource, List<Loi_vi_pham> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrLoivipham = arrContact;
    }
    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_tenloi, parent, false);
            viewHolder.tv_tenloi = convertView.findViewById(R.id.tv_Tenloi);
            viewHolder.tv_mucphat = convertView.findViewById(R.id.tv_mucphat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Loi_vi_pham loiViPham = arrLoivipham.get(position);
        viewHolder.tv_tenloi.setText(loiViPham.getTen_Loi());
        viewHolder.tv_mucphat.setText(loiViPham.getMuc_Phat());
        return convertView;
    }
    public class ViewHolder {
        TextView tv_tenloi, tv_mucphat;
    }
}
