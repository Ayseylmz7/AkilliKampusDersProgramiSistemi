public class DersFactory {
    public static Ders dersUret(String tip, String kod, String ad, int kredi) {
        if (tip.equalsIgnoreCase("ZORUNLU")) {
            return new ZorunluDers(kod, ad, kredi);
        } else if (tip.equalsIgnoreCase("SECMELI")) {
            return new SecmeliDers(kod, ad, kredi);
        }
        throw new IllegalArgumentException("Geçersiz ders tipi!");
    }
}