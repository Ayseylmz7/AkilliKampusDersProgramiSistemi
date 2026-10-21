package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static final String DOSYA_YOLU = "test_verileri.dat";

    // Verileri dosyaya kaydetme (Serialization)
    public static <T> void verileriKaydet(List<T> veriListesi) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOSYA_YOLU))) {
            oos.writeObject(veriListesi);
            System.out.println("Veriler başarıyla dosyaya kaydedildi.");
        } catch (IOException e) {
            System.err.println("Kayıt sırasında hata oluştu: " + e.getMessage());
        }
    }

    // Dosyadan verileri geri okuma
    @SuppressWarnings("unchecked")
    public static <T> List<T> verileriOku() {
        File dosya = new File(DOSYA_YOLU);
        if (!dosya.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DOSYA_YOLU))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Dosya okunurken hata oluştu: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // GEÇİCİ TEST METODU: Çalışıp çalışmadığını görmek için bunu çalıştırabilirsin
    public static void main(String[] args) {
        System.out.println("Test başlatılıyor...");

        // Test için basit bir liste oluşturalım (Not: Ders sınıfına Serializable eklenmiş olmalı)
        List<String> testListesi = new ArrayList<>();
        testListesi.add("Nesneye Yonelik Programlama");
        testListesi.add("Veri Yapilari");

        // Kaydetmeyi dene
        verileriKaydet(testListesi);

        // Okumayı dene
        List<String> okunanListe = verileriOku();
        System.out.println("Dosyadan okunan veriler: " + okunanListe);
    }
}


