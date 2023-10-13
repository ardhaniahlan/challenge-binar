package binarfud_3.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Menu> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(Menu item) {
        items.add(item);
    }

    public List<Menu> getItems() {
        return items;
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(Menu::getHarga)
                .sum();
    }

}
