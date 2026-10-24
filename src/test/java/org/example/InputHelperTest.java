package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

/** Girdi doğrulama (validasyon) testleri: klavye girişi sahte bir akışla taklit edilir. */
class InputHelperTest {

    private void girdi(String metin) {
        InputHelper.setGirdiKaynagi(new ByteArrayInputStream(metin.getBytes()));
    }

    @Test
    void readIntGecerliSayiyiKabulEder() {
        girdi("5\n");
        assertEquals(5, InputHelper.readInt("Sayı: ", 1, 10));
    }

    @Test
    void readIntHarfVeAralikDisiGirdiyiReddedipTekrarSorar() {
        girdi("abc\n99\n7\n");
        assertEquals(7, InputHelper.readInt("Sayı: ", 1, 10));
    }

    @Test
    void readNonEmptyStringBosGirdiyiReddeder() {
        girdi("\n   \nBLM201\n");
        assertEquals("BLM201", InputHelper.readNonEmptyString("Kod: "));
    }

    @Test
    void readConfirmationEveHayirYanitlariniAyirir() {
        girdi("x\nE\n");
        assertTrue(InputHelper.readConfirmation("Emin misiniz?"));
        girdi("h\n");
        assertFalse(InputHelper.readConfirmation("Emin misiniz?"));
    }
}
