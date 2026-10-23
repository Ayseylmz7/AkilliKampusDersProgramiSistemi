package org.example;

import java.io.Serializable;

public class ZamanDilimi implements Serializable {
    private static final long serialVersionUID = 1L;

    private String gun;
    private String saatAraligi;

    // Boş constructor (Java'nın dosyadan nesne okuyabilmesi için)
    public ZamanDilimi() {
    }

    // Parametreli constructor
    public ZamanDilimi(String gun, String saatAraligi) {
        this.gun = gun;
        this.saatAraligi = saatAraligi;
    }

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public String getSaatAraligi() {
        return saatAraligi;
    }

    public void setSaatAraligi(String saatAraligi) {
        this.saatAraligi = saatAraligi;
    }
}