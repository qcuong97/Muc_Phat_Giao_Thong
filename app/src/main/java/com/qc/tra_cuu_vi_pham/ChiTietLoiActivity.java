package com.qc.tra_cuu_vi_pham;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qc.tra_cuu_vi_pham.database.DatabaseHandler;
import com.qc.tra_cuu_vi_pham.database.Loi_vi_pham;
import com.qc.tra_cuu_vi_pham.database.addDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ChiTietLoiActivity extends AppCompatActivity {
    private ListView listView;
    List<Loi_vi_pham> loi_vi_phams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_loi);
        Anhxa();
        GetLoi();
    }
    private void GetLoi() {
        Intent intent = getIntent();
        String phuongtien = intent.getStringExtra("PhuongTien");
        int id_loi = intent.getIntExtra("ID_Loi",0);
        addDatabase addDatabase = new addDatabase(this);
        loi_vi_phams = addDatabase.getLoi(phuongtien,id_loi);
        final CustomAdapter customAdapter = new CustomAdapter(this,R.layout.layout_tenloi, loi_vi_phams);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutInflater layout = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layout.inflate(R.layout.chitiet_loi, null);
                TextView tv_ct = popupView.findViewById(R.id.tv_ctloi);
                TextView tv_mucphat = popupView.findViewById(R.id.tv_mucphat);
                TextView tv_hp_bs = popupView.findViewById(R.id.tv_hpbs);
                tv_ct.setText(loi_vi_phams.get(i).getCT_Loi());
                tv_mucphat.setText(loi_vi_phams.get(i).getMuc_Phat());
                tv_hp_bs.setText(loi_vi_phams.get(i).getHp_BS());
                Dialog dialog = new Dialog(ChiTietLoiActivity.this);
                dialog.setContentView(popupView);
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }
    private void Anhxa() {
        listView = findViewById(R.id.listview);

    }
}
