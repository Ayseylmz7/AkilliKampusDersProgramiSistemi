package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Veri kalıcılığı (serileştirme) testleri. */
class DataRepositoryTest {

    private String eskiDosyaYolu;
    private File gecici;

    @BeforeEach
    void hazirla() throws IOException {
        eskiDosyaYolu = DataRepository.getDosyaYolu();
        gecici = File.createTempFile("repo_test", ".dat");
        gecici.delete();
        DataRepository.setDosyaYolu(gecici.getAbsolutePath());
    }

    @AfterEach
    void temizle() {
        gecici.delete();
        DataRepository.setDosyaYolu(eskiDosyaYolu);
    }

    private DersProgramiKaydi ornekKayit(String tip, String kod, String ad) {
        return new DersProgramiKaydi(
                DersFactory.dersUret(tip, kod, ad, 3),
                new OgretimElemani("Dr.", "Ali Yılmaz"),
                new Derslik("B101", 40, "Projeksiyon"),
                new ZamanDilimi("Pazartesi", "09:00-10:50"),
                25);
    }

    @Test
    void dosyaYoksaBosListeDoner() {
        List<DersProgramiKaydi> liste = DataRepository.verileriOku();
        assertNotNull(liste);
        assertTrue(liste.isEmpty());
    }

    @Test
    void kaydedilenKayitTumAlanlariylaGeriOkunur() {
        List<DersProgramiKaydi> kayitlar = new ArrayList<>();
        kayitlar.add(ornekKayit("ZORUNLU", "BLM101", "Programlama"));
        DataRepository.verileriKaydet(kayitlar);

        List<DersProgramiKaydi> okunan = DataRepository.verileriOku();
        assertEquals(1, okunan.size());
        DersProgramiKaydi k = okunan.get(0);
        assertEquals("BLM101", k.getDers().getDersKodu());
        assertEquals("Programlama", k.getDers().getDersAdi());
        assertEquals(3, k.getDers().getKredi());
        assertEquals("Ali Yılmaz", k.getOgretimElemani().getAdSoyad());
        assertEquals("B101", k.getDerslik().getDerslikKodu());
        assertEquals("Pazartesi", k.getZamanDilimi().getGun());
        assertEquals(25, k.getOgrenciSayisi());
    }

    @Test
    void secmeliDersDeKaydedilipOkunabilir() {
        List<DersProgramiKaydi> kayitlar = new ArrayList<>();
        kayitlar.add(ornekKayit("SECMELI", "BLM301", "Yapay Zeka"));
        DataRepository.verileriKaydet(kayitlar);

        DersProgramiKaydi k = DataRepository.<DersProgramiKaydi>verileriOku().get(0);
        assertInstanceOf(SecmeliDers.class, k.getDers());
        assertEquals("Yapay Zeka", k.getDers().getDersAdi());
    }

    @Test
    void yenidenKaydetmeEskiVerininUstuneYazar() {
        List<DersProgramiKaydi> ilk = new ArrayList<>();
        ilk.add(ornekKayit("ZORUNLU", "A1", "Ders A"));
        ilk.add(ornekKayit("SECMELI", "A2", "Ders B"));
        DataRepository.verileriKaydet(ilk);

        List<DersProgramiKaydi> ikinci = new ArrayList<>();
        ikinci.add(ornekKayit("ZORUNLU", "A3", "Ders C"));
        DataRepository.verileriKaydet(ikinci);

        assertEquals(1, DataRepository.verileriOku().size());
    }
}
