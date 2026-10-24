package org.example;

import java.util.List;

public class ConsoleMenu {

    private final DersProgramiYoneticisi yonetici;

    public ConsoleMenu() {
        this.yonetici = DersProgramiYoneticisi.getInstance();
    }

    public void start() {
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = InputHelper.readInt("Seçiminiz [0-5]: ", 0, 5);

            switch (choice) {
                case 1 -> showCourseSubMenu();
                case 2 -> System.out.println("\n[Sistem] Derslik işlemleri modülü.");
                case 3 -> System.out.println("\n[Sistem] Öğretim Elemanı işlemleri modülü.");
                case 4 -> programKaydiEklemeEkrani();
                case 5 -> displayScheduleTable();
                case 0 -> {
                    if (InputHelper.readConfirmation("Programdan çıkmak istediğinize emin misiniz?")) {
                        System.out.println("\nSistemden çıkılıyor. İyi günler!");
                        running = false;
                    }
                }
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n==================================================");
        System.out.println("        AKILLI KAMPÜS DERS PROGRAMI SİSTEMİ       ");
        System.out.println("==================================================");
        System.out.println(" 1. Ders İşlemleri");
        System.out.println(" 2. Derslik İşlemleri");
        System.out.println(" 3. Öğretim Elemanı İşlemleri");
        System.out.println(" 4. Ders Programına Kayıt Ekle (Çakışma Kontrollü)");
        System.out.println(" 5. Haftalık Ders Programını Göster (Tablo)");
        System.out.println(" 0. Çıkış");
        System.out.println("==================================================");
    }

    private void showCourseSubMenu() {
        System.out.println("\n--- DERS İŞLEMLERİ MENÜSÜ ---");
        System.out.println(" 1. Yeni Ders Tanımla");
        System.out.println(" 0. Ana Menüye Dön");

        int subChoice = InputHelper.readInt("Seçiminiz [0-1]: ", 0, 1);
        if (subChoice == 1) {
            String courseCode = InputHelper.readNonEmptyString("Ders Kodu (Örn: BLM201): ");
            String courseName = InputHelper.readNonEmptyString("Ders Adı: ");
            System.out.println("✅ Ders kaydı alındı: " + courseCode + " - " + courseName);
        }
    }

    // Çakışma Kontrolü Çalıştıran Kayıt Ekranı (Tüm Constructor Yapıları Hizalandı)
    private void programKaydiEklemeEkrani() {
        System.out.println("\n--- YENİ DERS PROGRAMI KAYDI ---");

        // 1. Ders Bilgileri ve DersFactory Kullanımı
        String tip = InputHelper.readNonEmptyString("Ders Tipi (ZORUNLU / SECMELI): ");
        String dersKodu = InputHelper.readNonEmptyString("Ders Kodu (Örn: BLM201): ");
        String dersAdi = InputHelper.readNonEmptyString("Ders Adı: ");
        int kredi = InputHelper.readInt("Ders Kredisi: ", 1, 10);

        Ders ders = DersFactory.dersUret(tip, dersKodu, dersAdi, kredi);

        // 2. Öğretim Elemanı Bilgileri (unvan, adSoyad)
        String unvan = InputHelper.readNonEmptyString("Öğretim Elemanı Ünvanı (Örn: Dr. Öğr. Üyesi / Prof. Dr.): ");
        String akademisyenAd = InputHelper.readNonEmptyString("Öğretim Elemanı Ad Soyad: ");
        OgretimElemani hoca = new OgretimElemani(unvan, akademisyenAd);

        // 3. Derslik Bilgileri (derslikKodu, kapasite, ozellikler)
        String derslikKodu = InputHelper.readNonEmptyString("Derslik Kodu (Örn: Amfi-1): ");
        int kapasite = InputHelper.readInt("Derslik Kapasitesi: ", 1, 500);
        String ozellikler = InputHelper.readNonEmptyString("Derslik Özellikleri (Örn: Projeksiyon, Amfi): ");
        Derslik derslik = new Derslik(derslikKodu, kapasite, ozellikler);

        // 4. Zaman Dilimi Bilgileri
        int ogrenciSayisi = InputHelper.readInt("Derse Kayıtlı Öğrenci Sayısı: ", 1, 500);
        String gun = InputHelper.readNonEmptyString("Gün (Örn: Pazartesi): ");
        String saat = InputHelper.readNonEmptyString("Saat Aralığı (Örn: 09:00-11:00): ");
        ZamanDilimi zaman = new ZamanDilimi(gun, saat);

        // 5. Kayıt Oluşturma ve Çakışma Önleme Algoritmasını Çağırma
        DersProgramiKaydi yeniKayit = new DersProgramiKaydi(ders, hoca, derslik, zaman, ogrenciSayisi);
        yonetici.dersEkle(yeniKayit);
    }

    public void displayScheduleTable() {
        List<DersProgramiKaydi> kayitlar = yonetici.getProgramKayitlari();

        System.out.println("\n================================ HAFTALIK DERS PROGRAMI ==================================");
        System.out.printf("| %-12s | %-12s | %-20s | %-20s | %-10s |\n", "Gün", "Saat", "Ders Adı", "Öğretim Elemanı", "Derslik");
        System.out.println("+--------------+--------------+----------------------+----------------------+------------+");

        if (kayitlar == null || kayitlar.isEmpty()) {
            System.out.println("|                              Henüz eklenmiş bir ders kaydı yok.                          |");
        } else {
            for (DersProgramiKaydi k : kayitlar) {
                System.out.printf("| %-12s | %-12s | %-20s | %-20s | %-10s |\n",
                        k.getZamanDilimi().getGun(),
                        k.getZamanDilimi().getSaatAraligi(),
                        k.getDers().getDersAdi(),
                        k.getOgretimElemani().getAdSoyad(),
                        k.getDerslik().getDerslikKodu());
            }
        }
        System.out.println("==========================================================================================\n");
    }
}