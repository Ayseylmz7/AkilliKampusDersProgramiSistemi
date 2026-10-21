public class Derslik {
    private String derslikKodu;
    private int kapasite;
    private String ozellikler; // Örn: Lab, Projeksiyon vb.

    public Derslik(String derslikKodu, int kapasite, String ozellikler) {
        this.derslikKodu = derslikKodu;
        this.kapasite = kapasite;
        this.ozellikler = ozellikler;
    }

    public String getDerslikKodu() { return derslikKodu; }
    public int getKapasite() { return kapasite; }
}