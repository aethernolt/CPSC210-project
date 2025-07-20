package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

public class JsonTest {
    protected void checkGrocery(String name, BigDecimal price, Grocery g) {
        assertEquals(name, g.getName());
        assertEquals(price, g.getPrice());
    }

    protected void checkCategory(String name, ArrayList<Grocery> groceries, Category c) {
        assertEquals(name, c.getName());
        int i = 0;
        for (Grocery g : groceries) {
            assertEquals(g.getName(), c.returnGroceries().get(i).getName());
            assertEquals(g.getPrice(), c.returnGroceries().get(i).getPrice());
            i++;
        }
    }
}