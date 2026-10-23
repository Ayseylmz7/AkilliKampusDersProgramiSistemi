package com.odev;

public class ConsoleMenu {

    public void start() {
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = InputHelper.readInt("Seçiminiz [0-5]: ", 0, 5);

            switch (choice) {
                case 1 -> showCourseSubMenu();
                case 2 -> System.out.println("\n[Sistem] Derslik işlemleri modülü çağrılıyor...");
                case 3 -> System.out.println("\n[Sistem] Öğretim Elemanı işlemleri modülü çağrılıyor...");
                case 4 -> System.out.println("\n[Sistem] Otomatik planlama ve çakışma kontrolü çalıştırılıyor...");
                case 5 -> displaySampleScheduleTable();
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
        System.out.println(" 1. Ders İşlemleri (Ekle / Listele / Sil)");
        System.out.println(" 2. Derslik İşlemleri (Ekle / Listele / Sil)");
        System.out.println(" 3. Öğretim Elemanı İşlemleri");
        System.out.println(" 4. Otomatik Program Oluştur & Çakışma Kontrolü");
        System.out.println(" 5. Haftalık Ders Programını Göster (Tablo)");
        System.out.println(" 0. Çıkış");
        System.out.println("==================================================");
    }

    private void showCourseSubMenu() {
        System.out.println("\n--- DERS İŞLEMLERİ MENÜSÜ ---");
        System.out.println(" 1. Yeni Ders Ekle");
        System.out.println(" 2. Dersleri Listele");
        System.out.println(" 0. Ana Menüye Dön");

        int subChoice = InputHelper.readInt("Seçiminiz [0-2]: ", 0, 2);
        if (subChoice == 1) {
            String courseCode = InputHelper.readNonEmptyString("Ders Kodu (Örn: BLM201): ");
            String courseName = InputHelper.readNonEmptyString("Ders Adı: ");
            System.out.println("✅ Ders başarıyla eklendi: " + courseCode + " - " + courseName);
        }
    }

    public void displaySampleScheduleTable() {
        System.out.println("\n================================ HAFTALIK DERS PROGRAMI ==================================");
        System.out.printf("| %-10s | %-25s | %-25s | %-12s |\n", "Gün / Saat", "Ders Adı", "Öğretim Elemanı", "Derslik");
        System.out.println("+------------+---------------------------+---------------------------+--------------+");
        System.out.printf("| %-10s | %-25s | %-25s | %-12s |\n", "Pzt 09:00", "Yazılım Mühendisliği", "Dr. Ahmet Yılmaz", "Amfi-1");
        System.out.printf("| %-10s | %-25s | %-25s | %-12s |\n", "Pzt 11:00", "Veri Yapıları", "Doç. Dr. Ayşe Tan", "Lab-2");
        System.out.printf("| %-10s | %-25s | %-25s | %-12s |\n", "Sal 14:00", "Nesneye Yönelik Prog.", "Dr. Mehmet Kaya", "Derslik-3");
        System.out.println("==========================================================================================\n");
    }
}