package client.entity;

import java.util.ArrayList;
import java.util.List;


public class MonHoc {
    
    private int id;
    private String maMH;
    private String tenMonHoc;
    private String soTinChi;
    private List<Nhom> nhoms = new ArrayList();


    public List<Nhom> getNhom() {
        return nhoms;
    }

    public void setNhom(List<Nhom> nhom) {
        this.nhoms = nhom;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(String soTinChi) {
        this.soTinChi = soTinChi;
    }
}
