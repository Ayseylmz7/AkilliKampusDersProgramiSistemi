package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Factory Pattern: DersFactory doğru alt sınıfı üretmeli (çok biçimlilik). */
class DersFactoryTest {

    @Test
    void zorunluTipiZorunluDersUretir() {
        Ders ders = DersFactory.dersUret("ZORUNLU", "BLM101", "Programlama", 4);
        assertInstanceOf(ZorunluDers.class, ders);
        assertEquals("BLM101", ders.getDersKodu());
        assertEquals("Programlama", ders.getDersAdi());
        assertEquals(4, ders.getKredi());
    }

    @Test
    void secmeliTipiSecmeliDersUretir() {
        Ders ders = DersFactory.dersUret("SECMELI", "BLM301", "Yapay Zeka", 3);
        assertInstanceOf(SecmeliDers.class, ders);
        assertEquals("Yapay Zeka", ders.getDersAdi());
    }

    @Test
    void tipBuyukKucukHarfDuyarsizdir() {
        assertInstanceOf(ZorunluDers.class, DersFactory.dersUret("zorunlu", "A1", "A", 1));
        assertInstanceOf(SecmeliDers.class, DersFactory.dersUret("Secmeli", "A2", "B", 1));
    }

    @Test
    void gecersizTipHataFirlatir() {
        assertThrows(IllegalArgumentException.class,
                () -> DersFactory.dersUret("BILINMEYEN", "X1", "X", 2));
    }

    @Test
    void altSiniflarDersTipiyleKullanilabilir() {
        Ders[] dersler = {
                DersFactory.dersUret("ZORUNLU", "M1", "Matematik", 4),
                DersFactory.dersUret("SECMELI", "M2", "Felsefe", 2)
        };
        assertTrue(dersler[0] instanceof ZorunluDers);
        assertTrue(dersler[1] instanceof SecmeliDers);
    }
}
