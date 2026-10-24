package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Model sınıfları: kapsülleme (getter/setter) ve DersProgramiKaydi birleşimi. */
class VeriSinifiTest {

    @Test
    void bolumAdiDonerilir() {
        assertEquals("Bilgisayar Mühendisliği", new Bolum("Bilgisayar Mühendisliği").getBolumAdi());
    }

    @Test
    void ogretimElemaniBilgileriDonerilirVeGuncellenir() {
        OgretimElemani hoca = new OgretimElemani("Dr.", "Ali Yılmaz");
        assertEquals("Dr.", hoca.getUnvan());
        assertEquals("Ali Yılmaz", hoca.getAdSoyad());
        hoca.setUnvan("Prof. Dr.");
        assertEquals("Prof. Dr.", hoca.getUnvan());
    }

    @Test
    void derslikBilgileriDonerilirVeGuncellenir() {
        Derslik d = new Derslik("A201", 60, "Lab");
        assertEquals("A201", d.getDerslikKodu());
        assertEquals(60, d.getKapasite());
        assertEquals("Lab", d.getOzellikler());
        d.setKapasite(80);
        assertEquals(80, d.getKapasite());
    }

    @Test
    void zamanDilimiGunVeSaatDonerilir() {
        ZamanDilimi z = new ZamanDilimi("Pazartesi", "09:00-10:50");
        assertEquals("Pazartesi", z.getGun());
        assertEquals("09:00-10:50", z.getSaatAraligi());
    }

    @Test
    void dersProgramiKaydiTumBilgileriTasir() {
        Ders ders = DersFactory.dersUret("ZORUNLU", "BLM201", "Veri Yapıları", 4);
        OgretimElemani hoca = new OgretimElemani("Prof. Dr.", "Ayşe Demir");
        Derslik derslik = new Derslik("B101", 50, "Projeksiyon");
        ZamanDilimi zaman = new ZamanDilimi("Salı", "13:00-14:50");

        DersProgramiKaydi kayit = new DersProgramiKaydi(ders, hoca, derslik, zaman, 35);

        assertSame(ders, kayit.getDers());
        assertSame(hoca, kayit.getOgretimElemani());
        assertSame(derslik, kayit.getDerslik());
        assertSame(zaman, kayit.getZamanDilimi());
        assertEquals(35, kayit.getOgrenciSayisi());
    }
}
