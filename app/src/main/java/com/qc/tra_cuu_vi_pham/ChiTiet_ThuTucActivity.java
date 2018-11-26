package com.qc.tra_cuu_vi_pham;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChiTiet_ThuTucActivity extends AppCompatActivity {
    TextView tv_dangkyxe,tv_phitruocba,tv_rutdkxe,tv_thutuc3,tv_thutuc4,tv_thutuc5;
    RelativeLayout reka_dangky,rela_ruthoso,rela_caplai_cavet,rela_cap_cmnd,rela_caplai_cmnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet__thu_tuc);
        Anhxa();
        Textview_Action();
    }

    private void Textview_Action() {
        final int[] i = {0,0,0,0,0};
        tv_dangkyxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( i[0] == 0){
                    reka_dangky.setVisibility(View.VISIBLE);
                    i[0] =1;
                }
                else {
                    reka_dangky.setVisibility(View.GONE);
                }
            }
        });
        tv_rutdkxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( i[1] == 0){
                    rela_ruthoso.setVisibility(View.VISIBLE);
                    i[1]=1;
                }
                else {
                    rela_ruthoso.setVisibility(View.GONE);
                }
            }
        });
        tv_thutuc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( i[2] == 0){
                    rela_caplai_cavet.setVisibility(View.VISIBLE);
                    i[2]=1;
                }
                else {
                    rela_caplai_cavet.setVisibility(View.GONE);
                }
            }
        });
        tv_thutuc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( i[3] == 0){
                    rela_cap_cmnd.setVisibility(View.VISIBLE);
                    i[3]=1;
                }
                else {
                    rela_cap_cmnd.setVisibility(View.GONE);
                }
            }
        });
        tv_thutuc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( i[4] == 0){
                    rela_caplai_cmnd.setVisibility(View.VISIBLE);
                    i[4]=1;
                }
                else {
                    rela_caplai_cmnd.setVisibility(View.GONE);
                }
            }
        });
        tv_phitruocba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPhoto(618,333);
            }
        });
    }

    private void Anhxa() {
        reka_dangky = findViewById(R.id.reka_dangky);
        rela_ruthoso = findViewById(R.id.rela_rut_dkyxe);
        rela_caplai_cavet = findViewById(R.id.rela_caplai_dk_2);
        rela_cap_cmnd = findViewById(R.id.rela_cap_moi_cmnd_2);
        rela_caplai_cmnd = findViewById(R.id.rela_cap_lai_cmnd);
        tv_dangkyxe = findViewById(R.id.tv_dangkyxe);
        tv_phitruocba = findViewById(R.id.tv_phitruocba);
        tv_rutdkxe = findViewById(R.id.tv_rutdangkyxe);
        tv_thutuc3 = findViewById(R.id.tv_thutuc_3);
        tv_thutuc4 = findViewById(R.id.tv_thutuc_4);
        tv_thutuc5 = findViewById(R.id.tv_thutuc_5);
    }
    private void loadPhoto(int width, int height) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_fullimage_dialog,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView image = layout.findViewById(R.id.fullimage);
        Drawable drawable = getResources().getDrawable(R.drawable.phi_truoc_ba);
        image.setImageDrawable(drawable);
        imageDialog.setView(layout);
        imageDialog.create();
        imageDialog.show();
    }
}
