package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Singleton Pattern ve çakışma önleme algoritması testleri. */
class DersProgramiYoneticisiTest {

    private DersProgramiYoneticisi yonetici;
    private String eskiDosyaYolu;
    private File gecici;
    private Ders ders1;
    private Ders ders2;
    private OgretimElemani hoca1;
    private OgretimElemani hoca2;
    private Derslik b101;
    private Derslik b102;

    @BeforeEach
    void hazirla() throws IOException {
        // Gerçek test_verileri.dat dosyasına dokunmamak için geçici dosya kullanılır
        eskiDosyaYolu = DataRepository.getDosyaYolu();
        gecici = File.createTempFile("ders_programi_test", ".dat");
        gecici.delete();
        DataRepository.setDosyaYolu(gecici.getAbsolutePath());

        yonetici = DersProgramiYoneticisi.getInstance();
        yonetici.getProgramKayitlari().clear(); // Singleton olduğu için testler arası temizlik

        ders1 = DersFactory.dersUret("ZORUNLU", "BLM101", "Programlama", 4);
        ders2 = DersFactory.dersUret("SECMELI", "BLM301", "Yapay Zeka", 3);
        hoca1 = new OgretimElemani("Dr. Öğr. Üyesi", "Ali Yılmaz");
        hoca2 = new OgretimElemani("Prof. Dr.", "Ayşe Demir");
        b101 = new Derslik("B101", 50, "Projeksiyon");
        b102 = new Derslik("B102", 30, "Laboratuvar");
    }

    @AfterEach
    void temizle() {
        yonetici.getProgramKayitlari().clear();
        gecici.delete();
        DataRepository.setDosyaYolu(eskiDosyaYolu);
    }

    @Test
    void singletonHepAyniNesneyiDondurur() {
        assertSame(DersProgramiYoneticisi.getInstance(), DersProgramiYoneticisi.getInstance());
    }

    @Test
    void cakismasizDersBasariylaEklenir() {
        var kayit = new DersProgramiKaydi(ders1, hoca1, b101, new ZamanDilimi("Pazartesi", "09:00-10:50"), 40);
        assertTrue(yonetici.dersEkle(kayit));
        assertEquals(1, yonetici.getProgramKayitlari().size());
    }

    @Test
    void kapasiteYetersizseDersEklenmez() {
        var kayit = new DersProgramiKaydi(ders1, hoca1, b102, new ZamanDilimi("Pazartesi", "09:00-10:50"), 45);
        assertFalse(yonetici.dersEkle(kayit));
        assertTrue(yonetici.getProgramKayitlari().isEmpty());
    }

    @Test
    void ayniDerslikAyniSaatteCakismaVerir() {
        var zaman = new ZamanDilimi("Salı", "13:00-14:50");
        assertTrue(yonetici.dersEkle(new DersProgramiKaydi(ders1, hoca1, b101, zaman, 30)));
        assertFalse(yonetici.dersEkle(new DersProgramiKaydi(ders2, hoca2, b101, zaman, 20)));
        assertEquals(1, yonetici.getProgramKayitlari().size());
    }

    @Test
    void ayniOgretimElemaniAyniSaatteCakismaVerir() {
        var zaman = new ZamanDilimi("Çarşamba", "10:00-11:50");
        assertTrue(yonetici.dersEkle(new DersProgramiKaydi(ders1, hoca1, b101, zaman, 30)));
        assertFalse(yonetici.dersEkle(new DersProgramiKaydi(ders2, hoca1, b102, zaman, 20)));
    }

    @Test
    void ayniDerslikFarkliGundeKullanilabilir() {
        assertTrue(yonetici.dersEkle(new DersProgramiKaydi(ders1, hoca1, b101, new ZamanDilimi("Pazartesi", "09:00-10:50"), 30)));
        assertTrue(yonetici.dersEkle(new DersProgramiKaydi(ders2, hoca2, b101, new ZamanDilimi("Salı", "09:00-10:50"), 30)));
        assertEquals(2, yonetici.getProgramKayitlari().size());
    }

    @Test
    void ayniOgretimElemaniFarkliSaatteDersVerebilir() {
        assertTrue(yonetici.dersEkle(new DersProgramiKaydi(ders1, hoca1, b101, new ZamanDilimi("Perşembe", "09:00-10:50"), 30)));
        assertTrue(yonetici.dersEkle(new DersProgramiKaydi(ders2, hoca1, b101, new ZamanDilimi("Perşembe", "11:00-12:50"), 30)));
    }

    @Test
    void gunKarsilastirmasiBuyukKucukHarfDuyarsizdir() {
        assertTrue(yonetici.dersEkle(new DersProgramiKaydi(ders1, hoca1, b101, new ZamanDilimi("Cuma", "09:00-10:50"), 30)));
        assertFalse(yonetici.dersEkle(new DersProgramiKaydi(ders2, hoca2, b101, new ZamanDilimi("cuma", "09:00-10:50"), 30)));
    }

    @Test
    void basariliEklemeDosyayaKaydedilir() {
        yonetici.dersEkle(new DersProgramiKaydi(ders1, hoca1, b101, new ZamanDilimi("Pazartesi", "09:00-10:50"), 30));

        List<DersProgramiKaydi> dosyadan = DataRepository.verileriOku();
        assertEquals(1, dosyadan.size());
        assertEquals("Programlama", dosyadan.get(0).getDers().getDersAdi());
    }
}
