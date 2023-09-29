package binarfud_2.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Menu> items;

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
        double total = 0;
        for (Menu item : items) {
            total += item.getHarga();
        }
        return total;
    }
}
