package org.example;

import java.io.Serializable;

public class ZorunluDers extends Ders implements Serializable {
    private static final long serialVersionUID = 1L;

    // Java'nın dosyadan nesne okuyabilmesi (deserialization) için boş constructor
    public ZorunluDers() {
        super(null, null, 0);
    }

    // Üye 1'in yazdığı parametreli constructor (normal nesne üretmek için)
    public ZorunluDers(String dersKodu, String dersAdi, int kredi) {
        super(dersKodu, dersAdi, kredi);
    }
}