package com.odev;
public class ZamanDilimi {
    private String gun;
    private String saatAraligi;

    public ZamanDilimi(String gun, String saatAraligi) {
        this.gun = gun;
        this.saatAraligi = saatAraligi;
    }

    public String getGun() { return gun; }
    public String getSaatAraligi() { return saatAraligi; }
}