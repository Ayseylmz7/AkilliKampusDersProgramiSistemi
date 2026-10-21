package org.example;

import java.io.Serializable;

public class OgretimElemani implements Serializable {
    private static final long serialVersionUID = 1L;

    private String adSoyad;
    private String unvan;

    // Boş constructor (Java'nın dosyadan nesne okuyabilmesi için)
    public OgretimElemani() {
    }

    // Parametreli constructor
    public OgretimElemani(String unvan, String adSoyad) {
        this.unvan = unvan;
        this.adSoyad = adSoyad;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
}