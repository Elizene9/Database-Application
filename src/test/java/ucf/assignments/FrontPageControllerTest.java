package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrontPageControllerTest {

    @Test
    // Checks if values will be sorted properly
    void sortValuePressedTest() {

        List<Double> valuesSorted = new ArrayList<>();
        valuesSorted.add(15.0);
        valuesSorted.add(8.2);
        valuesSorted.add(100.7);

        List<Double> Compare = new ArrayList<>();
        Compare.add(8.2);
        Compare.add(15.0);
        Compare.add(100.7);


        Collections.sort(valuesSorted);
        System.out.println(valuesSorted);

        for (int i = 0; i < 3; i++) {

            assertEquals(Compare.get(i), valuesSorted.get(i));
        }


    }

    @Test
    // Checks if names are sorted properly
    void sortByNameTest() {

        FrontPageController.names.add("January");
        FrontPageController.names.add("February");
        FrontPageController.names.add("March");

        List<String> list = new ArrayList<>();

        list.add("February");
        list.add("January");
        list.add("March");

        Collections.sort(FrontPageController.names);
        for (int i = 0; i < 3; i++) {

            assertEquals(list.get(i), FrontPageController.names.get(i));
        }
    }

    @Test
    // Checks if serial numbers are sorted correctly
    void sortSerial() {
        FrontPageController.serials.add("XX99999999");
        FrontPageController.serials.add("XX33333333");
        FrontPageController.serials.add("XX55555555");

        List<String> list = new ArrayList<>();

        list.add("XX33333333");
        list.add("XX55555555");
        list.add("XX99999999");

        Collections.sort(FrontPageController.serials);
        for (int i = 0; i < 3; i++) {

            assertEquals(list.get(i), FrontPageController.serials.get(i));
        }
    }

    @Test
    // Checks if items are removed upon user choice
    void RemoveItemsUponUserSelection() {

        String userSelection = "Bob: XX77777777";
        List<String> list = new ArrayList<>();

        list.add("February: XX77777777");
        list.add("January: XX77777777");
        list.add("Bob: XX77777777");

        List<String> expect = new ArrayList<>();

        expect.add("February: XX77777777");
        expect.add("January: XX77777777");

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).equals(userSelection))
                list.remove(i);
        }

        for (int x = 0; x < expect.size(); x++) {
            assertEquals(expect.get(x), list.get(x));
        }
    }

    @Test
    // Tests if searched items are correctly identified
    void ChecksIfUserSearchIsFound() {
        List<String> list = new ArrayList<>();

        String searchSerial = "XX77777777";
        String name = "February";
        list.add("February: XX77777777");
        list.add("January: XX77777777");
        list.add("Bob: XX77777777");

        boolean flag = false;
        boolean flagTwo = false;

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).contains(searchSerial))
                flag = true;
        }

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).contains(name))
                flagTwo = true;
        }



        assertEquals(true, flag);
        assertEquals(true, flagTwo);
    }
}