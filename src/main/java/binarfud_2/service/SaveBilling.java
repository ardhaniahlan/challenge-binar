package binarfud_2.service;

import binarfud_2.model.Menu;
import binarfud_2.utils.Utils;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveBilling {
    public static void saveOrderToFile(List<Menu> orderItems) {
        String randomString = Utils.generateRandomString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ss-hh-mm-yyyy");
        String dateTime = dateFormat.format(new Date());
        String fileName = randomString + "-" + dateTime + "-" + "Invoice.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(Utils.SEPARATOR + "\n");
            writer.write("BinarFud\n");
            writer.write(Utils.SEPARATOR + "\n");
            writer.write("Terima kasih sudah memesan\ndi BinarFud\n\n");
            writer.write("Dibawah ini adalah pesanan anda:\n\n");

            for (Menu menu : orderItems) {
                writer.write(menu.getNama() + "\t" + menu.getJumlah() + "\t" + menu.getHarga() + "\n");
            }

            int totalQty = 0;
            int totalPrice = 0;
            for (Menu menu : orderItems) {
                totalQty += menu.getJumlah();
                totalPrice += menu.getHarga();
            }
            writer.write("-----------------------------+\n");
            writer.write("Total \t" + totalQty + "\t" + totalPrice + "\n");

            writer.write("Pembayaran : BinarCash\n");
            writer.write(Utils.SEPARATOR + "\n");
            writer.write("Simpan struk ini sebagai\nbukti pembayaran\n");
            writer.write(Utils.SEPARATOR + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan pesanan ke dalam file.");
            e.printStackTrace();
        }
    }
}
