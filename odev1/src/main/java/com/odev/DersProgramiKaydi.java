package com.odev;
public class DersProgramiKaydi {
    private Ders ders;
    private OgretimElemani ogretimElemani;
    private Derslik derslik;
    private ZamanDilimi zamanDilimi;
    private int ogrenciSayisi;

    public DersProgramiKaydi(Ders ders, OgretimElemani ogretimElemani, Derslik derslik, ZamanDilimi zamanDilimi, int ogrenciSayisi) {
        this.ders = ders;
        this.ogretimElemani = ogretimElemani;
        this.derslik = derslik;
        this.zamanDilimi = zamanDilimi;
        this.ogrenciSayisi = ogrenciSayisi;
    }

    public Ders getDers() { return ders; }
    public Derslik getDerslik() { return derslik; }
    public OgretimElemani getOgretimElemani() { return ogretimElemani; }
    public ZamanDilimi getZamanDilimi() { return zamanDilimi; }
    public int getOgrenciSayisi() { return ogrenciSayisi; }
}