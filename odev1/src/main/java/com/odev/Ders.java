package com.odev;
public abstract class Ders {
    private String dersKodu;
    private String dersAdi;
    private int kredi;

    public Ders(String dersKodu, String dersAdi, int kredi) {
        this.dersKodu = dersKodu;
        this.dersAdi = dersAdi;
        this.kredi = kredi;
    }

    public String getDersKodu() { return dersKodu; }
    public String getDersAdi() { return dersAdi; }
    public int getKredi() { return kredi; }
}