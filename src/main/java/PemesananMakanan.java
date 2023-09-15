import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PemesananMakanan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int jmlPesan;
        double totalHarga, harga;
        String hasil;
        List<String> history;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt", true))) {
            history = bacaRiwayatDariFile("history.txt");

            do {
                System.out.println("\nPilih operasi:");
                System.out.println("1. Nasi Goreng   | 15.000");
                System.out.println("2. Mie Goreng    | 13.000");
                System.out.println("3. Nasi + Ayam   | 18.000");
                System.out.println("4. Es Teh Manis  | 3.000");
                System.out.println("5. Es Jeruk      | 5.000");
                System.out.println("99. Pesan & Bayar");
                System.out.println("0. Keluar Aplikasi");
                System.out.print("Masukkan nomor operasi (1/2/3/4/5/99/0): ");
                int pilihan = input.nextInt();

                switch (pilihan) {
                    case 1:
                        harga = 15.000;
                        System.out.println("\n=> Nasi Goreng <=");
                        System.out.print("Jumlah Pesanan: ");
                        jmlPesan = input.nextInt();
                        totalHarga = harga * jmlPesan;

                        hasil = "Nasi Goreng   x" + jmlPesan + "   |" + totalHarga + "00";
                        history.add(hasil);
                        System.out.println("Pesanan ditambahkan");
                        break;
                    case 2:
                        harga = 13.000;
                        System.out.println("\n=> Mie Goreng <=");
                        System.out.print("Jumlah Pesanan: ");
                        jmlPesan = input.nextInt();
                        totalHarga = harga * jmlPesan;

                        hasil = "Mie Goreng    x" + jmlPesan + "   |" + totalHarga + "00";
                        history.add(hasil);
                        System.out.println("Pesanan ditambahkan");
                        break;
                    case 3:
                        harga = 18.000;
                        System.out.println("\n=> Nasi + Ayam <=");
                        System.out.print("Jumlah Pesanan: ");
                        jmlPesan = input.nextInt();
                        totalHarga = harga * jmlPesan;

                        hasil = "Nasi + Ayam   x" + jmlPesan + "   |" + totalHarga + "00";
                        history.add(hasil);
                        System.out.println("Pesanan ditambahkan");
                        break;
                    case 4:
                        harga = 3.000;
                        System.out.println("\n=> Es Teh Manis <=");
                        System.out.print("Jumlah Pesanan: ");
                        jmlPesan = input.nextInt();
                        totalHarga = harga * jmlPesan;

                        hasil = "Es Teh Manis  x" + jmlPesan + "   |" + totalHarga + "00";
                        history.add(hasil);
                        System.out.println("Pesanan ditambahkan");
                        break;
                    case 5:
                        harga = 5.000;
                        System.out.println("\n=> Es Jeruk <=");
                        System.out.print("Jumlah Pesanan: ");
                        jmlPesan = input.nextInt();
                        totalHarga = harga * jmlPesan;

                        hasil = "Es Jeruk      x" + jmlPesan + "   |" + totalHarga + "00";
                        history.add(hasil);
                        System.out.println("Pesanan ditambahkan");
                        break;
                    case 99:
                        System.out.println("\n================================");
                        System.out.println("Konfirmasi Pembayaran");
                        System.out.println("================================");

                        tampilkanRiwayat(history);
                        int jumlah = hitungPesan(history);
                        double total = hitungTotal(history);
                        System.out.println("-------------------------------+");
                        System.out.println("Total         " + jumlah + "    " + total + "00");

                        System.out.println("\n1.Konfirmasi dan Bayar");
                        System.out.println("2.Kembali ke Menu Utama");
                        System.out.println("3.Keluar Aplikasi");
                        System.out.print("=> ");
                        pilihan = input.nextInt();
                        if (pilihan == 1) {
                            cetakRiwayatKeFile(history,writer);
                            hitungPesan(history);
                            hitungTotal(history);
                            System.out.println("Simpan Struk");
                            break;
                        }
                        else if (pilihan == 2) main(args);
                        else if (pilihan == 3) System.exit(1);
                        break;
                    case 0:
                        System.exit(1);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }


            } while (true);

        } catch (IOException e) {
            System.out.println("Gagal menulis ke file history.txt.");
        }
    }

    public static void cetakRiwayatKeFile(List<String> history, BufferedWriter writer) {
        try {
            System.out.println("Menyimpan riwayat ke file history.txt...");
            for (String pesanan : history) {
                writer.write("\n================================");
                writer.write("\nBinarFud");
                writer.write("\n================================");

                writer.write("\n\nTerima kasih sudah memesan \ndi BinarFud");

                writer.write("\n\nDibawah ini adalah pesanan Anda");

                writer.write("\n\n" + pesanan + "\n");
                writer.write("-------------------------------+");
                writer.write("\nTotal         " + hitungPesan(history) + "    " + hitungTotal(history) + "00");


                writer.write("\n\nPembayaran : BinarCash");

                writer.write("\n\n================================");
                writer.write("\nSimpan struk ini sebagai \nbukti pembayaran");
                writer.write("\n================================");
            }
            System.out.println("Riwayat telah dicetak ke dalam file history.txt.");
        } catch (IOException e) {
            System.out.println("Gagal mencetak riwayat ke file history.txt.");
        }
    }

    public static void tampilkanRiwayat(List<String> history) {
        for (String pesan : history) {
            System.out.println(pesan);
        }
    }

    public static List<String> bacaRiwayatDariFile(String fileName) {
        List<String> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file " + fileName);
        }
        return history;
    }

    public static double hitungTotal(List<String> history) {
        double total = 0.0;
        for (String operasi : history) {
            String[] parts = operasi.split("\\|");
            if (parts.length == 2) {
                try {
                    double hasilOperasi = Double.parseDouble(parts[1].trim());
                    total += hasilOperasi;
                } catch (NumberFormatException ignored) {

                }
            }
        }
        return total;
    }

    public static int hitungPesan(List<String> history) {
        int total = 0;
        for (String operasi : history) {
            String[] parts = operasi.split("\\|");
            if (parts.length == 2) {
                String[] subparts = parts[0].split("x");
                if (subparts.length == 2) {
                    try {
                        int jumlahPesanan = Integer.parseInt(subparts[1].trim());
                        total += jumlahPesanan;
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }
        return total;
    }
}