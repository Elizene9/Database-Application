/*
 * UCF COP 3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Charlene Creighton
 */

package ucf.assignments;
import java.math.BigDecimal;

public class Items
{
    public String name;
    public BigDecimal value;
    public String Serial;

    Items()
    {
        name = "";
        value = BigDecimal.valueOf(0.00);
        Serial = "0000000000";
    }

    Items(String myName, BigDecimal myValue, String mySerial)
    {
        name = myName;
        value = myValue;
        Serial = mySerial;
    }
}
