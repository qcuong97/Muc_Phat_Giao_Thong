package com.qc.tra_cuu_vi_pham;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.qc.tra_cuu_vi_pham.database.DatabaseHandler;
import com.qc.tra_cuu_vi_pham.database.Loi_vi_pham;
import com.qc.tra_cuu_vi_pham.database.addDatabase;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {
    Button bt_tk, bt_xoatext,bt_luuy;
    CardView bt_xemay,bt_oto,bt_thutuc;
    SharedPreferences sharedPreferences;
    Spinner spinner;
    private String Phuong_Tien = "XM";
    EditText et_TK;
    Dialog dialog;
    ListView listView;
    private  Intent intent;
    private AdView mAdView;
    private static InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        Buton_action();
        Create_database();
        Admod_Banner();
        Admod_InterstittialAD();
    }

    private void Admod_InterstittialAD() {
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        mInterstitialAd.setAdUnitId("");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded(){
                Log.i("Load_InterstitialAd","Load Thanh Cong");
            }
        });
    }
    public static void showInterstittialAD(){
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        else {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
    }
    private void Admod_Banner() {
        MobileAds.initialize(this, "");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    private void Create_database() {
        addDatabase addDatabase = new addDatabase(MainActivity.this);
        sharedPreferences = getSharedPreferences("create_data",MODE_PRIVATE);
        if (sharedPreferences.getInt("created",0) == 0){
            addDatabase.setLoi_vi_pham_xemay();
            addDatabase.setLoi_vi_pham_oto();
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("created",1);
            editor.apply();
        }
    }

    private void Buton_action() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if( i == 1){
                    Phuong_Tien = "OTO";
                }
                else {
                    Phuong_Tien = "XM";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bt_xemay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,NhomloiActivity.class);
                intent.putExtra("PhuongTien","XM");
                startActivity(intent);
            }
        });
        bt_oto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,NhomloiActivity.class);
                intent.putExtra("PhuongTien","OTO");
                startActivity(intent);
            }
        });
        bt_thutuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,ChiTiet_ThuTucActivity.class);
                startActivity(intent);
                showInterstittialAD();
            }
        });
        bt_tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);
                String keywork = String.valueOf(et_TK.getText());
                List<Loi_vi_pham> loi_vi_phams = databaseHandler.searchLoi(Phuong_Tien,keywork);
                if ( !loi_vi_phams.isEmpty()){
                    ShowDialog(loi_vi_phams);
                }
                else {
                    ShowAlertDialog();
                }
            }
        });
        bt_xoatext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_TK.setText("");
                bt_xoatext.setVisibility(View.GONE);
            }
        });
        bt_luuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LuuYActivity.class));
            }
        });
        et_TK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    bt_xoatext.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public  void ShowDialog(final List<Loi_vi_pham> loi_vi_phams){
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_listview);
        dialog.setCancelable(true);
        listView = dialog.findViewById(R.id.listview_dialog);
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,R.layout.layout_tenloi,loi_vi_phams);
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
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(popupView);
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        dialog.show();
    }
    public  void ShowAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tìm Kiếm");
        builder.setMessage("Không tìm thấy!!");
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void Anhxa() {
        bt_xemay = findViewById(R.id.bt_xemay);
        bt_tk = findViewById(R.id.bt_tiemkiem);
        bt_thutuc = findViewById(R.id.bt_thutuc);
        et_TK = findViewById(R.id.et_TK);
        bt_oto = findViewById(R.id.bt_oto);
        bt_luuy = findViewById(R.id.bt_luuy);
        spinner = findViewById(R.id.spinner);
        bt_xoatext = findViewById(R.id.bt_xoatext);
        String[] items = new String[]{"Xe máy", "Ô tô"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.mess)
                .setTitle(R.string.dialog_title);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                }
                Uri uri = Uri.parse("market://details?id=" + appPackageName);
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
                System.exit(0);

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
