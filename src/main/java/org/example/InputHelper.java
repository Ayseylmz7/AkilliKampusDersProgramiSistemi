package org.example;

import java.io.InputStream;
import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    // Birim testlerinde klavye girişini taklit edebilmek için
    public static void setGirdiKaynagi(InputStream in) { scanner = new Scanner(in); }

    // Güvenli tam sayı alma metodu
    public static int readInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("❌ Hata: Lütfen " + min + " ile " + max + " arasında bir değer girin.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Hata: Geçersiz bir sayı girdiniz! Lütfen tekrar deneyin.");
            }
        }
    }

    // Boş metin girilmesini engelleyen metot
    public static String readNonEmptyString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Hata: Bu alan boş bırakılamaz!");
        }
    }

    // Onay alma (Evet/Hayır) metodu
    public static boolean readConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (E/H): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("E")) return true;
            if (input.equals("H")) return false;
            System.out.println("❌ Hata: Lütfen sadece 'E' veya 'H' girin.");
        }
    }
}