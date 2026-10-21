package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Akilli Kampus Ders Programi Sistemi Baslatiliyor...");

        // 1. Yoneticiyi cagiriyoruz (Dosyadan varsa eski kayitlari okur)
        DersProgramiYoneticisi yonetici = DersProgramiYoneticisi.getInstance();
        System.out.println("Baslangictaki kayit sayisi: " + yonetici.getProgramKayitlari().size());

        // 2. Test icin nesneler olusturalim
        Ders ders = new ZorunluDers("BLM101", "Nesneye Yonelik Programlama", 5);
        OgretimElemani hoca = new OgretimElemani("Ahmet", "Prof. Dr.");
        Derslik derslik = new Derslik("D101", 50, "Amfi"); // Veya hata mesajındaki sıraya göre
        ZamanDilimi zaman = new ZamanDilimi("Pazartesi", "09:00-11:00");

        // 3. Yeni kayit olustur ve ekle
        DersProgramiKaydi yeniKayit = new DersProgramiKaydi(ders, hoca, derslik, zaman, 30);
        boolean eklendiMi = yonetici.dersEkle(yeniKayit);

        if (eklendiMi) {
            System.out.println("Test Basarili! Yeni kayit eklendi ve dosyaya kaydedildi.");
        }

        System.out.println("Guncel toplam kayit sayisi: " + yonetici.getProgramKayitlari().size());
    }
}