package binarfud_3.view;

import binarfud_3.model.Menu;
import binarfud_3.utils.Utils;

import java.util.List;
import java.util.stream.IntStream;

public class FoodOrderView {

    public void welcomeMessage() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Selamat Datang di BinarFud");
        System.out.println(Utils.SEPARATOR);
    }

    public void printMenu(List<Menu> menu) {
        System.out.println("Silahkan pilih makanan :");
        IntStream.range(0, menu.size())
                .forEach(i -> System.out.println((i + 1) + ". " + menu.get(i).getNama() + Utils.TABLE + menu.get(i).getHarga()));
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("=> ");
    }

    public void orderSummary(List<Menu> order) {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println(Utils.SEPARATOR);

        order.forEach(menu -> System.out.println(menu.getNama() + Utils.TABLE + menu.getJumlah() + Utils.TABLE + menu.getHarga()));
    }

    public void totalAmount(int totalJumlah, double totalHarga) {
        System.out.println("-----------------------------+");
        System.out.println("Total \t| " + totalJumlah + Utils.TABLE + totalHarga);
    }

    public void thankYouMessage() {
        System.out.println("Terima kasih sudah memesan\ndi BinarFud");
        System.out.println();
    }

    public void paymentOptions() {
        System.out.println("Pembayaran : BinarCash");
        System.out.println(Utils.SEPARATOR);
        System.out.println("Simpan struk ini sebagai\nbukti pembayaran");
        System.out.println(Utils.SEPARATOR);
    }

    public void choice() {
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
        System.out.print("=> ");
    }

    public void invoicePayment() {
        System.out.println();
        System.out.println(Utils.SEPARATOR);
        System.out.println("BinarFud");
        System.out.println(Utils.SEPARATOR);
    }

    public void hitungMakanView(Menu makanan) {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Berapa Pesanan anda");
        System.out.println(Utils.SEPARATOR);

        System.out.println(makanan.getNama() + "\t|\t" + makanan.getHarga());
        System.out.println("(input 0 untuk kembali)");
        System.out.print("qty => ");
    }

    public void errorInputMessage() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Mohon masukkan input\nPilihan anda");
        System.out.println(Utils.SEPARATOR);
        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
        System.out.print("=> ");
    }

    public void errorInputZero() {
        System.out.println(Utils.SEPARATOR);
        System.out.println("Minimal 1 Jumlah\nPesanan!");
        System.out.println(Utils.SEPARATOR);
    }

}
