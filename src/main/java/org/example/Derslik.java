package org.example;

import java.io.Serializable;

public class Derslik implements Serializable {
    private static final long serialVersionUID = 1L;

    private String derslikKodu;
    private int kapasite;
    private String ozellikler;

    public Derslik(String derslikKodu, int kapasite, String ozellikler) {
        this.derslikKodu = derslikKodu;
        this.kapasite = kapasite;
        this.ozellikler = ozellikler;
    }

    public Derslik() {
    }

    public String getDerslikKodu() {
        return derslikKodu;
    }

    public void setDerslikKodu(String derslikKodu) {
        this.derslikKodu = derslikKodu;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public String getOzellikler() {
        return ozellikler;
    }

    public void setOzellikler(String ozellikler) {
        this.ozellikler = ozellikler;
    }
}