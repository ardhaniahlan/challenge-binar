package binarfud_3.service;

import binarfud_3.model.Menu;
import binarfud_3.utils.Utils;
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

            orderItems.stream()
                    .map(menu -> menu.getNama() + "\t" + menu.getJumlah() + "\t" + menu.getHarga() + "\n")
                    .forEach(data -> {
                        try {
                            writer.write(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            int totalQty = orderItems.stream()
                    .mapToInt(Menu::getJumlah)
                    .sum();
            double totalPrice = orderItems.stream()
                    .mapToDouble(Menu::getHarga)
                    .sum();

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
