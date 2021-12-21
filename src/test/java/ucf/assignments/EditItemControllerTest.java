package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditItemControllerTest {

    @Test
    // Check if user enters valid name
    void itemNameEntered() {

        String trueName = "January";
        String falseName = "x";
        String check;
        boolean flag = false;

        check = trueName;

        if (check.length() < 2 || check.length() > 256)
            flag = false;

        else
            flag = true;

        assertEquals(true, flag);

        check = falseName;
        if (check.length() < 2 || check.length() > 256)
            flag = false;

        else
            flag = true;

        assertEquals(false, flag);
    }

}