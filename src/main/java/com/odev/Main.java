import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        System.out.println("İLK DEGERİ GİRİNİZ:");
        Scanner input = new Scanner (System.in) ;
        int sayi1 = input.nextInt () ;

        System.out.println("İKİNCİ DEGERİ GİRİNİZ:");
        Scanner input2 = new Scanner (System.in) ;
        int sayi2 = input2.nextInt () ;

        System.out.println("SONUC:");
        System.out.println(sayi1+sayi2);
    }
}