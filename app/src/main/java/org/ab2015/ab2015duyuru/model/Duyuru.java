package org.ab2015.ab2015duyuru.model;


public class Duyuru{

    private String duyuruBaslik;
    private String duyuruIcerik;

    public Duyuru(String duyuruBaslik, String duyuruIcerik) {
        this.duyuruBaslik = duyuruBaslik;
        this.duyuruIcerik = duyuruIcerik;
    }

    public String getDuyuruBaslik() {
        return duyuruBaslik;
    }

    public void setDuyuruBaslik(String duyuruBaslik) {
        this.duyuruBaslik = duyuruBaslik;
    }

    public String getDuyuruIcerik() {
        return duyuruIcerik;
    }

    public void setDuyuruIcerik(String duyuruIcerik) {
        this.duyuruIcerik = duyuruIcerik;
    }

    @Override
    public String toString() {
        return "Duyuru{" +
                "duyuruBaslik='" + duyuruBaslik + '\'' +
                ", duyuruIcerik='" + duyuruIcerik + '\'' +
                '}';
    }
}
