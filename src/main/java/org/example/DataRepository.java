package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static String DOSYA_YOLU = "test_verileri.dat";

    // Birim testlerinin gerçek veri dosyasını bozmaması için dosya yolu değiştirilebilir
    public static void setDosyaYolu(String yol) { DOSYA_YOLU = yol; }
    public static String getDosyaYolu() { return DOSYA_YOLU; }

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
}
