package client.entity;


import java.util.ArrayList;
import java.util.List;


public class Nhom {
    
    private int id;
    private int idNhom;
    private String maLop;
    private int siSo;
    private List<Buoi> buois = new ArrayList();
    private MonHoc monHoc;

    public int getIdNhom() {
        return idNhom;
    }

    public void setIdNhom(int idNhom) {
        this.idNhom = idNhom;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public List<Buoi> getBuois() {
        return buois;
    }

    public void setBuois(List<Buoi> buois) {
        this.buois = buois;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
