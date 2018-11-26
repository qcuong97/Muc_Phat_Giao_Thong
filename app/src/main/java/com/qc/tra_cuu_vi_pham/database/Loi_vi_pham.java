package com.qc.tra_cuu_vi_pham.database;

public class Loi_vi_pham {
    private int ID;
    private int ID_Nhom_Loi;
    private String Phuong_Tien;
    private String Ten_Loi;
    private String Muc_Phat;
    private String Hp_BS;
    private String CT_Loi;

    public String getHp_BS() {
        return Hp_BS;
    }

    public void setHp_BS(String hp_BS) {
        Hp_BS = hp_BS;
    }

    public String getCT_Loi() {
        return CT_Loi;
    }

    public void setCT_Loi(String CT_Loi) {
        this.CT_Loi = CT_Loi;
    }

    public String getTen_Loi_Ko_Dau() {
        return Ten_Loi_Ko_Dau;
    }

    public void setTen_Loi_Ko_Dau(String ten_Loi_Ko_Dau) {
        Ten_Loi_Ko_Dau = ten_Loi_Ko_Dau;
    }

    private String Ten_Loi_Ko_Dau;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_Nhom_Loi() {
        return ID_Nhom_Loi;
    }

    public void setID_Nhom_Loi(int ID_Nhom_Loi) {
        this.ID_Nhom_Loi = ID_Nhom_Loi;
    }

    public String getPhuong_Tien() {
        return Phuong_Tien;
    }

    public void setPhuong_Tien(String phuong_Tien) {
        Phuong_Tien = phuong_Tien;
    }

    public String getTen_Loi() {
        return Ten_Loi;
    }

    public void setTen_Loi(String ten_Loi) {
        Ten_Loi = ten_Loi;
    }

    public String getMuc_Phat() {
        return Muc_Phat;
    }

    public void setMuc_Phat(String muc_Phat) {
        Muc_Phat = muc_Phat;
    }



    public Loi_vi_pham(){}

    public Loi_vi_pham(int ID,int ID_Nhom_Loi,String Phuong_Tien,String Ten_Loi,String Muc_Phat,String Hinh_Phat_BS,String Ten_Loi_Ko_Dau,String CT_Loi){
        this.ID = ID;
        this.ID_Nhom_Loi = ID_Nhom_Loi;
        this.Phuong_Tien = Phuong_Tien;
        this.Ten_Loi = Ten_Loi;
        this.Muc_Phat = Muc_Phat;
        this.Ten_Loi_Ko_Dau = Ten_Loi_Ko_Dau;
        this.Hp_BS = Hinh_Phat_BS;
        this.CT_Loi = CT_Loi;
    }
    public Loi_vi_pham (String Ten_Loi, String Muc_phat,String CT_Loi,String hp_BS){
        this.Ten_Loi = Ten_Loi;
        this.Muc_Phat = Muc_phat;
        this.Hp_BS = hp_BS;
        this.CT_Loi = CT_Loi;
    }
}
