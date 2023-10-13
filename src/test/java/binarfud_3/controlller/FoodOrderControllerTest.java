package binarfud_3.controlller;

import static org.junit.jupiter.api.Assertions.*;

import binarfud_3.model.Menu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FoodOrderControllerTest {
    List<Menu> menu = new ArrayList<>();

    @Test
    void testJumlahMenuSuccess() {
        Menu foodItem1 = new Menu("Nasi Goreng", 15000);
        Menu foodItem2 = new Menu("Mie Goreng", 13000);

        menu.add(foodItem1);
        menu.add(foodItem2);

        for (Menu menuItem : menu) {
            assertNotEquals(2, menuItem.getJumlah());
        }
    }
}

