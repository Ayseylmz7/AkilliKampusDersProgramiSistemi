# Akıllı Kampüs Ders Programı Sistemi

Bu proje, bir üniversite kampusündeki derslerin, dersliklerin, öğretim elemanlarının ve ders programlarının çakışmasız bir şekilde yönetilmesini sağlayan Java tabanlı bir konsol uygulamasıdır.

## 🛠 Kullanılan Teknolojiler ve Tasarım Kalıpları
* **Java 17+**
* **Singleton Pattern:** `DersProgramiYoneticisi` sınıfı ile tüm program verilerinin tek bir merkezden yönetimi sağlanır.
* **Factory Pattern:** `DersFactory` sınıfı ile nesne oluşturma mantığı soyutlaştırılmıştır.
* **Nesne Yönelimli Programlama (OOP):** Kalıtım, soyutlama ve kapsülleme ilkeleri uygulanmıştır.

## 🚀 Öne Çıkan Özellikler
* **Çakışma Kontrol Algoritması:** Aynı derslik veya aynı öğretim elemanına aynı gün ve saatte birden fazla ders atanmasını engeller.
* **Girdi Doğrulama (Input Validation):** `InputHelper` modülü ile hatalı veri girişleri ve çökme durumları engellenir.
* **Kapasite Kontrolü:** Derslik kapasitesini aşan öğrenci kayıtlarında sistem uyarı verir.
* **Tablosal Görünüm:** Eklenen ders programı haftalık formatta konsola basılır.

## 💻 Konsol Arayüzü Kullanımı
1. Uygulama çalıştırıldığında ana menü üzerinden işlem seçimi yapılır.
2. `4` seçeneği ile yeni ders programı kaydı açılır.
3. Ders tipi, öğretmen, derslik ve zaman dilimi bilgileri girilir.
4. Sistem çakışma yoksa kaydı onaylar, çakışma varsa hatayı konsola basar.
