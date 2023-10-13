package binarfud_3.controlller;

import binarfud_3.model.Menu;
import binarfud_3.model.Order;
import binarfud_3.service.SaveBilling;
import binarfud_3.view.FoodOrderView;

import java.util.*;
import java.util.stream.Stream;

public class FoodOrderController {
    private final List<Menu> menu = new ArrayList<>();
    private final Order order = new Order();
    private final FoodOrderView view = new FoodOrderView();
    private boolean finishOrder = false;
    private boolean finishInput = false;
    private static final Scanner inpScanner = new Scanner(System.in);

    public void startOrder() {
        view.welcomeMessage();
        loadMenu();

        Stream.generate(() -> !finishOrder)
                .takeWhile(condition -> condition)
                .forEach(condition -> {
                    displayMenu();
                    pilihMakanan();
                });
    }


    void loadMenu() {
        menu.add(new Menu("Nasi Goreng", 15000));
        menu.add(new Menu("Mie Goreng", 13000));
        menu.add(new Menu("Nasi + Ayam", 18000));
        menu.add(new Menu("Es Teh Manis", 3000));
        menu.add(new Menu("Es Jeruk", 5000));
    }

    private void displayMenu() {
        view.printMenu(menu);
    }

    private void pilihMakanan() {
        try {
            Optional<Integer> inputUser = Optional.of(inpScanner.nextInt());

            inputUser.ifPresent(input -> {
                if (input > 0 && input <= menu.size()) {
                    hitungMakanan(menu.get(input - 1));
                } else {
                    if (input == 99) {
                        if (order.getItems().isEmpty()) {
                            view.errorInputZero();
                        } else {
                            konfirmasiPembayaran();
                        }
                    } else if (input == 0) {
                        finishOrder = true;
                    }
                }
            });
        } catch (InputMismatchException e) {
            finishInput = false;
            salahInput();
        }
    }

    private void salahInput() {
        if (finishInput) {
            finishInput = false;
        } else {
            Stream.generate(() -> {
                view.errorInputMessage();
                Optional<String> inputUser = Optional.of(inpScanner.next());

                inputUser.ifPresent(input -> {
                    if (input.equals("Y")) {
                        finishInput = true;
                    } else if (input.equals("N")) {
                        System.exit(0);
                    }
                });
                return false;
            }).filter(Boolean::booleanValue).findFirst();
        }
    }

    private void hitungMakanan(Menu makanan) {
        view.hitungMakanView(makanan);
        try {
            Optional<Integer> qtyInputUser = Optional.of(inpScanner.nextInt());

            qtyInputUser.ifPresent(input -> {
                if (input > 0) {
                    order.getItems().stream()
                            .filter(orderItem -> orderItem.getNama().equals(makanan.getNama()))
                            .findFirst()
                            .map(orderItem -> {
                                orderItem.setJumlah(orderItem.getJumlah() + input);
                                orderItem.setHarga(orderItem.getHarga() + (input * makanan.getHarga()));
                                return true;
                            })
                            .orElseGet(() -> {
                                order.addItem(new Menu(makanan.getNama(), input, input * makanan.getHarga()));
                                return false;
                            });
                }
            });
        } catch (InputMismatchException e) {
            finishInput = false;
            salahInput();
        }
    }


    private void konfirmasiPembayaran() {
        view.orderSummary(order.getItems());
        double total = order.calculateTotal();
        int totalQty = order.getItems().stream().mapToInt(Menu::getJumlah).sum();
        view.totalAmount(totalQty, total);
        view.paymentOptions();
        view.choice();
        try {
            Optional<Integer> inputUser = Optional.of(inpScanner.nextInt());

            inputUser.ifPresent(input -> {
                if (input == 1) {
                    invoicePayment();
                } else if (input == 2) {
                    finishOrder = false;
                    displayMenu();
                    pilihMakanan();
                } else if (input == 0) {
                    System.exit(0);
                }
            });
        } catch (InputMismatchException e) {
            finishInput = false;
            salahInput();
        }
    }

    private void invoicePayment() {
        view.invoicePayment();
        view.thankYouMessage();
        view.orderSummary(order.getItems());
        view.paymentOptions();
        SaveBilling.saveOrderToFile(order.getItems());
        System.exit(0);
    }
}
