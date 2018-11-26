package com.qc.tra_cuu_vi_pham.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Muc_Vi_Pham_Giao_Thong";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME_2 = "Loi_vi_pham";
    private static final String KEY_ID_CT_Loi = "ID_Loi_Vi_Pham";
    private static final String KEY_ID_Nhom_Loi = "ID_Nhom_LOi";
    private static final String KEY_Phuong_Tien = "Phuong_Tien";
    private static final String KEY_Ten_Loi = "Ten_Loi";
    private static final String KEY_Muc_Phat = "Muc_Phat";
    private static final String KEY_Ten_Loi_Ko_Dau = "Loi_Ko_Dau";
    private static final String KEY_HP_BS = "Hinh_Phat_Bo_Sung";
    private static final String KEY_CT_Loi = "Chi_Tiet_Loi";
    private ContentValues contentValues = new ContentValues();
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_Loi_Vi_Pham = String.format("CREATE TABLE %s(%s TEXT PRIMARYKEY,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%S TEXT,%s TEXT,%s TEXT)"
                ,TABLE_NAME_2,KEY_ID_CT_Loi,KEY_ID_Nhom_Loi,KEY_Phuong_Tien,KEY_Ten_Loi,KEY_Muc_Phat,KEY_Ten_Loi_Ko_Dau,KEY_HP_BS,KEY_CT_Loi);
        sqLiteDatabase.execSQL(create_table_Loi_Vi_Pham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void addLoi_Vi_Pham(Loi_vi_pham loi_vi_pham){
        SQLiteDatabase sqLiteDatabase_write = getWritableDatabase();
        contentValues.put(KEY_ID_CT_Loi,loi_vi_pham.getID());
        contentValues.put(KEY_ID_Nhom_Loi,loi_vi_pham.getID_Nhom_Loi());
        contentValues.put(KEY_Phuong_Tien,loi_vi_pham.getPhuong_Tien());
        contentValues.put(KEY_Ten_Loi,loi_vi_pham.getTen_Loi());
        contentValues.put(KEY_Muc_Phat,loi_vi_pham.getMuc_Phat());
        contentValues.put(KEY_Ten_Loi_Ko_Dau,loi_vi_pham.getTen_Loi_Ko_Dau());
        contentValues.put(KEY_HP_BS,loi_vi_pham.getHp_BS());
        contentValues.put(KEY_CT_Loi,loi_vi_pham.getCT_Loi());
        sqLiteDatabase_write.insert(TABLE_NAME_2,null,contentValues);
        sqLiteDatabase_write.close();
    }
    public List<Loi_vi_pham> getLoi_vi_pham(String phuongtien,int id){
        SQLiteDatabase sqLiteDatabase_read = getReadableDatabase();
        List<Loi_vi_pham> loi_vi_phamList = new ArrayList<>();
        String query = "SELECT"+ " " +KEY_Ten_Loi+ ", "+ KEY_Muc_Phat +", "+ " " +KEY_HP_BS+ ", "+ KEY_CT_Loi +" "+ "FROM "+ TABLE_NAME_2 +" "+ "WHERE "+KEY_ID_Nhom_Loi +"=" + id+" " + "and"+" "+ KEY_Phuong_Tien + "=" + "'"+phuongtien+"'";
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase_read.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Loi_vi_pham loi_vi_pham = new Loi_vi_pham(cursor.getString(0),
                    cursor.getString(1),cursor.getString(3),cursor.getString(2));
            loi_vi_phamList.add((loi_vi_pham));
            cursor.moveToNext();
        }
        cursor.close();
        return loi_vi_phamList;
    }
    public List<Loi_vi_pham> searchLoi(String phuongtien,String keywork){
        SQLiteDatabase sqLiteDatabase_read = getReadableDatabase();
        List<Loi_vi_pham> loi_vi_phamList = new ArrayList<>();
        String query = "SELECT"+ " " +KEY_Ten_Loi+ ", "+ KEY_Muc_Phat +", "+" " +KEY_HP_BS+ ", "+ KEY_CT_Loi +" "+  "FROM "+ TABLE_NAME_2 +" "+ "WHERE ("+KEY_Ten_Loi
                +" "+ "LIKE" + " "+ "'%"+ keywork+"%'" +" "  + " "+"OR" + " "+KEY_Ten_Loi_Ko_Dau
                +" "+ "LIKE" + " "+ "'%"+ keywork + "%')" +" "+ "AND"+" "+ KEY_Phuong_Tien + "=" + "'"+phuongtien+"'" ;
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase_read.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Loi_vi_pham loi_vi_pham = new Loi_vi_pham(cursor.getString(0),
                    cursor.getString(1),cursor.getString(3),cursor.getString(2));
            loi_vi_phamList.add((loi_vi_pham));
            cursor.moveToNext();
        }
        return loi_vi_phamList;
    }
}
