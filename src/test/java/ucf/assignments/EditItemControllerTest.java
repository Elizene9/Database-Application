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

    @Test
    void itemValueEntered() {

        String value = "18";
        boolean flag = false;

        if (Double.parseDouble(value) == 18)
            flag = true;

        assertEquals(true, flag);
    }

    @Test
    // Checks if valid serial number is entered
    void itemSerialEntered() {

        String serial = "XX55555555";
        String falseSerial = "XX9999999";
        String badSerial = "&&&8888889";
        char[] mySerial = serial.toCharArray();
        char[] myFalseSerial = falseSerial.toCharArray();
        char[] myBadSerial = badSerial.toCharArray();
        boolean flag = true, flagTwo = true, flagThree = true;


        for (int i = 0; i < serial.length(); i++) {

            if (!Character.isDigit(mySerial[i]) && !Character.isLetter(mySerial[i]) ||mySerial.length != 10)
                flag = false;

            if (!Character.isDigit(myBadSerial[i]) && !Character.isLetter(myBadSerial[i]) ||myBadSerial.length != 10)
                flagThree = false;
        }

        for (int j =0; j < myFalseSerial.length; j++)
            if (!Character.isDigit(myFalseSerial[j]) && !Character.isLetter(myFalseSerial[j]) ||myFalseSerial.length != 10)
                flagTwo = false;

        assertEquals(true, flag);
        assertEquals(false, flagTwo);
        assertEquals(false, flagThree);

    }

    @Test
    void editPressed() {
    }
}