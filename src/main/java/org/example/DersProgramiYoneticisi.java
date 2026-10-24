package org.example;

import java.util.ArrayList;
import java.util.List;

public class DersProgramiYoneticisi {
    private static DersProgramiYoneticisi instance;
    private List<DersProgramiKaydi> programKayitlari;

    private DersProgramiYoneticisi() {
        // ENTEGRASYON 1: Program ilk açıldığında verileri dosyadan okuyup yüklüyoruz
        programKayitlari = DataRepository.verileriOku();
        if (programKayitlari == null) {
            programKayitlari = new ArrayList<>();
        }
    }

    // Singleton Pattern: Tekil nesne üretimi
    public static synchronized DersProgramiYoneticisi getInstance() {
        if (instance == null) {
            instance = new DersProgramiYoneticisi();
        }
        return instance;
    }

    // ÇAKIŞMA ÖNLEME ALGORİTMASI
    public boolean dersEkle(DersProgramiKaydi yeniKayit) {
        // 1. Kapasite Kontrolü[cite: 6]
        if (yeniKayit.getDerslik().getKapasite() < yeniKayit.getOgrenciSayisi()) {
            System.out.println("Hata: Derslik kapasitesi yetersiz!");
            return false;
        }

        // 2. Çakışma Kontrolleri[cite: 6]
        for (DersProgramiKaydi mevcut : programKayitlari) {
            boolean ayniGun = mevcut.getZamanDilimi().getGun().equalsIgnoreCase(yeniKayit.getZamanDilimi().getGun());
            boolean ayniSaat = mevcut.getZamanDilimi().getSaatAraligi().equalsIgnoreCase(yeniKayit.getZamanDilimi().getSaatAraligi());

            if (ayniGun && ayniSaat) {
                // Derslik çakışması[cite: 6]
                if (mevcut.getDerslik().getDerslikKodu().equals(yeniKayit.getDerslik().getDerslikKodu())) {
                    System.out.println("Çakışma Hatası: Bu derslik seçilen saatte dolu!");
                    return false;
                }
                // Öğretim elemanı çakışması[cite: 6]
                if (mevcut.getOgretimElemani().getAdSoyad().equals(yeniKayit.getOgretimElemani().getAdSoyad())) {
                    System.out.println("Çakışma Hatası: Öğretim elemanının bu saatte başka dersi var!");
                    return false;
                }
            }
        }

        programKayitlari.add(yeniKayit);

        // ENTEGRASYON 2: Yeni ders başarıyla eklendiğinde güncel listeyi dosyaya kaydediyoruz
        DataRepository.verileriKaydet(programKayitlari);

        System.out.println("Başarılı: Ders çakışma olmadan programa eklendi.");
        return true;
    }

    public List<DersProgramiKaydi> getProgramKayitlari() {
        return programKayitlari;
    }
}