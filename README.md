# 🏫 Akıllı Kampüs Ders Programı Sistemi

> **Java SE, Nesne Yönelimli Programlama (OOP) İlkeleri ve Tasarım Kalıpları (Design Patterns) ile Geliştirilmiş Akıllı Ders Programı ve Çakışma Yönetimi Mimarisi**

Bu proje; üniversite bünyesindeki derslerin, akademisyenlerin, dersliklerin ve haftalık zaman dilimlerinin dinamik ve esnek bir şekilde yönetilmesini sağlayan nesne yönelimli bir konsol uygulamasıdır. Sistem, ders programı oluşturulurken ortaya çıkabilecek zaman, derslik, kapasite ve öğretim elemanı çakışmalarını nesne seviyesinde tespit eder ve veritabanı/bellek tutarlılığını korur.

---

## 🛠 Proje Mimarisi ve Tasarım Kalıpları (Design Patterns)

Proje, ölçeklenebilir ve sürdürülebilir yazılım mimarisi prensiplerine uygun olarak tasarlanmış olup bünyesinde şu tasarım kalıplarını barındırır:

### 1. Singleton Pattern (`DersProgramiYoneticisi`)
* Proje genelinde tek bir ders programı yönetim merkezinin bulunmasını garanti eder.
* `getInstance()` metodu ile bellekte tekil bir nesne (instance) oluşturulur; böylece çakışma kontrol algoritmaları tek bir veri havuzu üzerinden tutarlı bir şekilde çalışır.

### 2. Factory Pattern (`DersFactory`)
* Ders nesnelerinin oluşturulma süreçlerini istemci (client) koddan soyutlar.
* `DersFactory.dersUret(tip, kod, ad, kredi)` metodu parametreye bağlı olarak polimorfik yapıda `ZorunluDers` veya `SecmeliDers` nesneleri türetir.

### 3. Nesne Yönelimli Programlama (OOP) Prensipleri
* **Polimorfizm & Soyutlama (Abstraction):** `Ders` sınıfı `abstract` olarak tanımlanmış, temel ders nitelikleri (kod, ad, kredi) soyutlanarak alt sınıflara aktarılmıştır.
* **Kapsülleme (Encapsulation):** Tüm veri modellerinde veri gizleme ilkesine uyulmuş, alanlar `private` tutularak erişim ve güncellemeler kontrollü Getter/Setter metotları ile sağlanmıştır.

---

## 📐 Modül Dağılımı ve Sorumluluklar

Proje 4 ana katman/modül halinde geliştirilmiştir:

1. **Konsol Kullanıcı Arayüzü (Console UI - `ConsoleMenu`):** 
   * Kullanıcı ile sistem arasındaki etkileşimi yönetir.
   * Menü seçimlerini yönlendirir ve servis metodlarını çağırır.

2. **Girdi Doğrulama ve Güvenlik Katmanı (`InputHelper`):** 
   * Kullanıcıdan alınan verilerin tür ve aralık kontrollerini yapar.
   * Hatalı veri tiplerini (harf yerine sayı girilmesi, boş geçilmesi vb.) yakalayarak uygulamanın çökmesini (crash) engeller.

3. **Çakışma Kontrol ve İş Mantığı Algoritması (`DersProgramiYoneticisi`):**
   * **Kapasite Kontrolü:** Derslik kapasitesinin derse kayıtlı öğrenci sayısından az olması durumunda kaydı engeller.
   * **Derslik Çakışması Kontrolü:** Aynı dersliğe aynı gün ve saat aralığında ikinci bir ders verilmesini engeller.
   * **Öğretim Elemanı Çakışması Kontrolü:** Bir akademisyene aynı gün ve saatte birden fazla ders atanmasını engeller.

4. **Domain Veri Modelleri (`Model` Katmanı):**
   * `Bolum`, `Ders`, `Derslik`, `OgretimElemani`, `ZamanDilimi` ve `DersProgramiKaydi` sınıfları ile alan analizi yapılmış modüler yapı.

---

## 💻 Kullanım Senaryoları ve Konsol Tablo Çıktısı

### Programın Çalıştırılması:
1. `Main.java` başlatılır.
2. Ana menüden `4` seçilerek **"Ders Programına Kayıt Ekle"** adımı açılır.
3. Ders tipi (`ZORUNLU` / `SECMELI`), kodu, adı, kredisi, akademisyen ünvan ve adı, derslik bilgileri ve zaman aralığı girilir.
4. Sistem çakışma yoksa kaydı onaylar, çakışma varsa ilgili hatayı ekrana basar.
5. Ana menüden `5` seçilerek haftalık ders programı hizalı tablo formatında görüntülenir.

```text
================================ HAFTALIK DERS PROGRAMI ==================================
| Gün          | Saat         | Ders Adı             | Öğretim Elemanı      | Derslik    |
+--------------+--------------+----------------------+----------------------+------------+
| Pazartesi    | 09:00-11:00  | Yazılım Mühendisliği | Dr. Öğr. Üyesi Ahmet | Amfi-1     |
==========================================================================================
