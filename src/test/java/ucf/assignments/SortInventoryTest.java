package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortInventoryTest
{

    @Test
    // Checks if values will be sorted properly
    void sortValuePressedTest()
    {
        Inventory.allItems.clear();
        Items item1 = new Items("Bob", BigDecimal.valueOf(10.00), "XX00000000");
        Items item2 = new Items("Abby", BigDecimal.valueOf(50.00), "AA00000000");
        Items item3 = new Items("Dan", BigDecimal.valueOf(20.00), "FF00000000");
        Items item4 = new Items("Carol", BigDecimal.valueOf(10.00), "BB00000000");

        List<Items> list = new ArrayList<>();
        list.add(item1);
        list.add(item4);
        list.add(item3);
        list.add(item2);

        Inventory.allItems.add(item1);
        Inventory.allItems.add(item2);
        Inventory.allItems.add(item3);
        Inventory.allItems.add(item4);
        Inventory.sortValue();

        for (int i = 0; i < list.size(); i++)
        {
            assertEquals(list.get(i).name, Inventory.allItems.get(i).name);
            assertEquals(list.get(i).value, Inventory.allItems.get(i).value);
            assertEquals(list.get(i).Serial, Inventory.allItems.get(i).Serial);
        }

    }

    @Test
    // Checks if names are sorted properly
    void sortByNameTest() {
        Inventory.allItems.clear();
        Items item1 = new Items("Bob", BigDecimal.valueOf(10.00), "XX00000000");
        Items item2 = new Items("Abby", BigDecimal.valueOf(50.00), "AA00000000");
        Items item3 = new Items("Dan", BigDecimal.valueOf(20.00), "FF00000000");
        Items item4 = new Items("Carol", BigDecimal.valueOf(10.00), "BB00000000");

        List<Items> list = new ArrayList<>();
        list.add(item2);
        list.add(item1);
        list.add(item4);
        list.add(item3);

        Inventory.allItems.add(item1);
        Inventory.allItems.add(item2);
        Inventory.allItems.add(item3);
        Inventory.allItems.add(item4);
        Inventory.sortName();
        for (int i = 0; i < list.size(); i++)
        {
            assertEquals(list.get(i).name, Inventory.allItems.get(i).name);
            assertEquals(list.get(i).value, Inventory.allItems.get(i).value);
            assertEquals(list.get(i).Serial, Inventory.allItems.get(i).Serial);
        }

    }

    @Test
    // Checks if serial numbers are sorted correctly
    void sortSerial()
    {
        Inventory.allItems.clear();
        Items item1 = new Items("Bob", BigDecimal.valueOf(10.00), "XX00000000");
        Items item2 = new Items("Abby", BigDecimal.valueOf(50.00), "AA00000000");
        Items item3 = new Items("Dan", BigDecimal.valueOf(20.00), "FF00000000");
        Items item4 = new Items("Carol", BigDecimal.valueOf(10.00), "BB00000000");

        List<Items> list = new ArrayList<>();
        list.add(item2);
        list.add(item4);
        list.add(item3);
        list.add(item1);

        Inventory.allItems.add(item1);
        Inventory.allItems.add(item2);
        Inventory.allItems.add(item3);
        Inventory.allItems.add(item4);
        Inventory.sortSerial();
        for (int i = 0; i < list.size(); i++)
        {
            assertEquals(list.get(i).name, Inventory.allItems.get(i).name);
            assertEquals(list.get(i).value, Inventory.allItems.get(i).value);
            assertEquals(list.get(i).Serial, Inventory.allItems.get(i).Serial);
        }
    }
}