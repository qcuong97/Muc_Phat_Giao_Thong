package com.qc.tra_cuu_vi_pham;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NhomloiActivity extends AppCompatActivity {
    Button bt_vachke,bt_bienbao,bt_tocdo,bt_dungdo,bt_chuyenhuong,bt_docon,bt_loikhac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomloi);
        Anhxa();
        Button_action();

    }
    public String getPhuongtien(){
        Intent intent = getIntent();
        return  intent.getStringExtra("PhuongTien");
    }
    public void sendPhuongtien_loi(String phuongtien, int id_loi){
        Intent intent = new Intent(NhomloiActivity.this,ChiTietLoiActivity.class);
        intent.putExtra("PhuongTien",phuongtien);
        intent.putExtra("ID_Loi",id_loi);
        startActivity(intent);
    }
    private void Button_action() {
        bt_vachke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPhuongtien_loi(getPhuongtien(),1);
            }
        });
        bt_bienbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPhuongtien_loi(getPhuongtien(),2);
            }
        });
        bt_tocdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPhuongtien_loi(getPhuongtien(),3);
                MainActivity.showInterstittialAD();
            }
        });
        bt_dungdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPhuongtien_loi(getPhuongtien(),4);
            }
        });
        bt_chuyenhuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPhuongtien_loi(getPhuongtien(),5);
                MainActivity.showInterstittialAD();
            }
        });
        bt_docon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPhuongtien_loi(getPhuongtien(),6);
            }
        });
        bt_loikhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPhuongtien_loi(getPhuongtien(),7);
                MainActivity.showInterstittialAD();
            }
        });
    }

    private void Anhxa() {
        bt_vachke = findViewById(R.id.bt_vachkeduong);
        bt_bienbao = findViewById(R.id.bt_bienbao);
        bt_tocdo = findViewById(R.id.bt_tocdo);
        bt_dungdo = findViewById(R.id.bt_dungdo);
        bt_chuyenhuong = findViewById(R.id.bt_chuyenhuong);
        bt_docon = findViewById(R.id.bt_nongdocon);
        bt_loikhac = findViewById(R.id.bt_loikhac);
    }
}
