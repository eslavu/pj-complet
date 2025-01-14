package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TesteCalculator
{
    @Test
    void test1_suma()
    {
        Calculator calculator = new Calculator(4,5);
        assertEquals(9, calculator.suma());
    }

    @Test
    void test2_suma()
    {
        Calculator calculator = new Calculator(2,2);
        assertTrue(calculator.suma() == 4);
    }

    @Test
    void test3_suma()
    {
        Calculator calculator = new Calculator(3,7);
        assertFalse(calculator.suma() != 10);
    }
}
